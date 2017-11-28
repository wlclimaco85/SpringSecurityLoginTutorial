package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Test
	public void contextLoads() throws InterruptedException {
        String encoded = passwordEncoder.encode("Test1234");
        Thread.sleep(5L * 1000L);
        Assert.isTrue(passwordEncoder.matches("Test1234", encoded));
        Assert.isTrue(!passwordEncoder.matches("Test123ss", encoded));
        Assert.isTrue(passwordEncoder.matches("Test1234", encoded));

        System.out.println("Test 1: "+passwordEncoder.matches("Test1234", encoded));
        System.out.println("Test 2: "+passwordEncoder.matches("Test123ss", encoded));
        System.out.println("Test 3: "+passwordEncoder.matches("Test1234", encoded));
	}

}
