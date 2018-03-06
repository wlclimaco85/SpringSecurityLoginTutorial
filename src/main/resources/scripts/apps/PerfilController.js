/**
 * Created by Y.Kamesh on 4/13/2015.
 */

angular.module('App.Admin')
    .controller('PerfilController', ['$scope', '$rootScope', '$location', 'AuthService','$http','$interval', 'jogoFactory', PerfilController])
function PerfilController($scope, $rootScope, $location, AuthService, $http, $interval, jogoFactory,toastr,localStorageService) {
    var vm = this;

    $scope.loading = true;
    var globals = JSON.parse(localStorage.getItem('globals'));
    var oUser = globals.currentUser;

    var fnCallback = function(oResp)
    {
        $scope.loading = false;
        $scope.user = oResp[0];
    }

    AuthService.fetchUserByUserId(oUser, fnCallback);
};
