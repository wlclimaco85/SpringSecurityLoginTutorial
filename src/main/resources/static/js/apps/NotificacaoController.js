/**
 * Created by Y.Kamesh on 4/13/2015.
 */

angular.module('App.Admin')
    .controller('NotificacaoController', ['$scope', '$rootScope', '$location', 'AuthService','$http','$interval', 'jogoFactory', NotificacaoController])
function NotificacaoController($scope, $rootScope, $location, AuthService, $http, $interval, jogoFactory,localStorageService,toastr) {
    var vm = this;

    $scope.loading = true;
    var globals = JSON.parse(localStorage.getItem('globals'));
    var oUser = globals.currentUser;
    var oNotificacaoRequest = {userId : oUser.id,empresaId : 82,role : oUser.roles[0]};
    AuthService.fetchAllNotificacoes(oNotificacaoRequest, function (response) {
        debugger
        vm.notificacoesList = response.result.notificacaoList;
        $scope.loading = false;
        // if (resp && resp.code==200) {
        //      AuthService.createJWTToken(resp.result.user, resp.result.token);
        //     AuthService.setCredentials();
        //     $location.path('/app');
        //  $scope.quadras = 
        
    });

   
    
};
