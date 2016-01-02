angular.module('quizApp', [
    'quizApp.controllers',
    'quizApp.services'
]).run(function($http){
    $http.defaults.useXDomain = true;
});