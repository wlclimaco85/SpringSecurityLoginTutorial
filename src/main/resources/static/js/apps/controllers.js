var app = angular.module('YWP2Web.controllers', ['YWP2Web.services'])

app.controller('AppCtrl', function($scope, $ionicLoading, $ionicModal, $timeout, $state) {
    $scope.show = function() {
		$ionicLoading.show({
			template: '<ion-spinner class="base-spinner" icon="android"></ion-spinner>'
		});
	};
	
	$scope.hide = function(){
		$ionicLoading.hide();
	};
})

