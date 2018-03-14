(function() {
  'use strict';
  angular.module('wdApp.tasks', []).controller('TaskController', [
    '$scope', 'UtilStorage', 'filterFilter', '$rootScope', 'toastr', 'toastrConfig', function($scope, UtilStorage, filterFilter, $rootScope, toastr, toastrConfig) {
      toastrConfig.positionClass = 'toast-bottom-right';
      toastrConfig.closeButton = true;
      var STORAGE_ID   = "tasks";
      var STORAGE_FILE = "./demodata/tasks.json";
    var tasks;
      UtilStorage.get(STORAGE_ID, STORAGE_FILE,false,function(res){
        tasks = $scope.tasks = res;
    
        $scope.newTask = '';
        $scope.remainingCount = filterFilter(tasks, {
          completed: false
        }).length;
      });
      $scope.editedTask = null;
      $scope.statusFilter = {
        completed: false
      };
      $scope.filter = function(filter) {
        switch (filter) {
          case 'all':
            return $scope.statusFilter = '';
          case 'active':
            return $scope.statusFilter = {
              completed: false
            };
          case 'completed':
            return $scope.statusFilter = {
              completed: true
            };
        }
      };
      $scope.add = function() {
        var newTask;
        newTask = $scope.newTask.trim();
        if (newTask.length === 0) {
          return;
        }
        tasks.push({
          title: newTask,
          completed: false
        });
		toastr.success('New task: "' + newTask + '" added');
        UtilStorage.put(STORAGE_ID, tasks);
        $scope.newTask = '';
        return $scope.remainingCount++;
      };
      $scope.edit = function(task) {
        return $scope.editedTask = task;
      };
      $scope.doneEditing = function(task) {
        $scope.editedTask = null;
        task.title = task.title.trim();
        if (!task.title) {
          $scope.remove(task);
        } else {
		  toastr.info('Task updated.', 'Information');
        }
        return UtilStorage.put(STORAGE_ID, tasks);
      };
      $scope.remove = function(task) {
        var index;
        $scope.remainingCount -= task.completed ? 0 : 1;
        index = $scope.tasks.indexOf(task);
        $scope.tasks.splice(index, 1);
        UtilStorage.put(STORAGE_ID, tasks);
        return toastr.warning('Task removed!', 'Warning');
      };
      $scope.completed = function(task) {
        $scope.remainingCount += task.completed ? -1 : 1;
        UtilStorage.put(STORAGE_ID, tasks);
        if (task.completed) {
          if ($scope.remainingCount > 0) {
            if ($scope.remainingCount === 1) {
              return toastr.info('Almost there! Only ' + $scope.remainingCount + ' task left', 'Information');
            } else {
              return toastr.info('Good job! Only ' + $scope.remainingCount + ' tasks left', 'Information');
            }
          } else {
            return toastr.success('Congrats! All done :)');
          }
        }
      };
      $scope.clearCompleted = function() {
        $scope.tasks = tasks = tasks.filter(function(val) {
          return !val.completed;
        });
        return UtilStorage.put(STORAGE_ID, tasks);
      };
      $scope.markAll = function(completed) {
        tasks.forEach(function(task) {
          return task.completed = completed;
        });
        $scope.remainingCount = completed ? 0 : tasks.length;
        UtilStorage.put(STORAGE_ID, tasks);
        if (completed) {
          return toastr.success('Congrats! All done :)');
        }
      };
      $scope.$watch('remainingCount == 0', function(val) {
        return $scope.allChecked = val;
      });
      return $scope.$watch('remainingCount', function(newVal, oldVal) {
        return $rootScope.$broadcast('taskRemaining:changed', newVal);
      });
    }
  ]);

}).call(this);

