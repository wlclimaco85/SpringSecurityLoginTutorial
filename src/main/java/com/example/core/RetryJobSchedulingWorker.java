package com.example.core;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.model.Job;
import com.example.model.entity.helper.CategoryPriorityComparator;
import com.example.service.JobService;

/**
 * Created by Y.Kamesh on 8/5/2015.
 */
public class RetryJobSchedulingWorker extends AbstractJobSchedulingWorker {
    private static Logger LOG = LoggerFactory.getLogger(RetryJobSchedulingWorker.class);

    @Autowired
    private JobService jobService;

    private ExecutorService executorService = Executors.newFixedThreadPool(50);

    /**
     * Runs every fifteen minutes.
     */
    @Scheduled(fixedDelay = 1000)
    public void scheduleRetryJobsForExecution() {
        LOG.info("Fetching failed jobs as per category and submit time priority...");
        List<Job> retryableJobs = jobService.fetchFailedJobsToBeScheduledForExecutionPerSubmissionTimePriority(10);
        LOG.info("Fetched Jobs Count: "+retryableJobs.size());
        Collections.sort(retryableJobs, new CategoryPriorityComparator());

        Map<Future<Job>, Job> result = new LinkedHashMap<>();
        for(Job retryableJob : retryableJobs) {
            try {
                LOG.info("Scheduling Job: name="+retryableJob.getName());
                retryableJob.setScheduledTime(new Date(System.currentTimeMillis()));
                retryableJob.setRetryCount(retryableJob.getRetryCount()+1);
                retryableJob.setStatus(Job.Status.RETRYING);
                jobService.update(retryableJob);
                result.put(executorService.submit(new JobExecutionThread(retryableJob)), retryableJob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOG.info("Done scheduling the job retrials");
        LOG.info("Waiting on results of the job retrials");

        LOG.info("Fetching results of the jobs");

        processResults(result, jobService, LOG);
    }
}
