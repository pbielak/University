angular.module('quizApp.services', ['ngResource'])
    .factory('QuestionService',['$resource', function($resource) {
        return $resource('http://localhost:8080/question/:quizId');
    }])
    .factory('QuizService', ['$resource', function($resource) {
        return $resource('http://localhost:8080/quiz')
    }]);