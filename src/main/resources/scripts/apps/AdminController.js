/**
 * Created by Y.Kamesh on 4/8/2015.
 */
angular.module('MobileAngularUiExamples')
    .controller('AdminController', ['$rootScope', '$location', AdminController]);

function AdminController($rootScope, $location) {
    var admin = this;
    
    admin.currentUser = null;
};