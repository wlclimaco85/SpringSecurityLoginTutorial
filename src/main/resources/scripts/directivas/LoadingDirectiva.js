/**
 * Created by Y.Kamesh on 4/13/2015.
 */
angular.module('App.loadings', []).directive('loading', function () {
      return {
        restrict: 'E',
        replace:true,
       // template: '<div class="loading"><img src="http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif" width="20" height="20" />LOADING...</div>',

       template: '<div class="loader loading">Loading...</div>',//'<div class="lds-dual-ring loading"></div>',
       //'<div class="loading"><div class="sk-fading-circle ">'+
        //    '<div class="sk-circle1 sk-circle"></div>'+
        //    '<div class="sk-circle2 sk-circle"></div>'+
        //    '<div class="sk-circle3 sk-circle"></div>'+
        //    '<div class="sk-circle4 sk-circle"></div>'+
        //    '<div class="sk-circle5 sk-circle"></div>'+
        //    '<div class="sk-circle6 sk-circle"></div>'+
        //    '<div class="sk-circle7 sk-circle"></div>'+
        //    '<div class="sk-circle8 sk-circle"></div>'+
        //    '<div class="sk-circle9 sk-circle"></div>'+
        //    '<div class="sk-circle10 sk-circle"></div>'+
        //    '<div class="sk-circle11 sk-circle"></div>'+
        //    '<div class="sk-circle12 sk-circle"></div>'+
       // '</div></div>',
        link: function (scope, element, attr) {
              scope.$watch('loading', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
      }
  });