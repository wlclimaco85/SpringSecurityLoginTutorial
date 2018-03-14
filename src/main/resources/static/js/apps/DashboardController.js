angular.module("App.Auth", ["chart.js"])
// Optional configuration
.config(['ChartJsProvider', function (ChartJsProvider) {
  // Configure all charts
  ChartJsProvider.setOptions({
    chartColors: ['#FF5252', '#FF8A80'],
    responsive: false
  });
  // Configure all line charts
  ChartJsProvider.setOptions('line', {
    showLines: false
  });
}])
.controller("DashboardController", ['$scope', '$timeout', function ($scope, $timeout) {

    $scope.labels = ["Reservados", "Livres", "A Confirmar"];
    $scope.data = [300, 500, 100];
$scope.onClick = function (points, evt) {
  console.log(points, evt);
};


}]);