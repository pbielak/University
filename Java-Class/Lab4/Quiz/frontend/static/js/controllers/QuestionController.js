angular.module('quizApp.controllers', [])
    .controller('MainController', ['$scope', 'QuestionService', 'QuizService', function($scope, QuestionService, QuizService) {
        this.quizzes = QuizService.query();
        this.chosenQuizId = 0;
        this.isQuizSet = false;
        this.setQuiz = function(id) {
            this.chosenQuizId = id;
            this.isQuizSet = true;
        };


        this.questions = QuestionService.query({quizId : this.chosenQuizId});

        this.goodAnswerCounter = 0;

        this.currentAnswer = "";
        this.checkedCurrentQuestion = false;
        this.isGoodAnswer = 0;

        this.checkQuestion = function() {
            this.checkedCurrentQuestion = true;
            var answers = this.questions[this.currentQuestionIndex].answers;

            for(var i = 0; i < answers.length; i++) {
                if(answers[i].answer == this.currentAnswer) {
                    if(answers[i].is_true) {
                        this.goodAnswerCounter++;
                        this.isGoodAnswer = 1;
                        document.getElementById('nextButton').className =  'btn btn-success btn-lg';
                    }
                    else {
                        this.isGoodAnswer = -1;
                        document.getElementById('nextButton').className =  'btn btn-danger btn-lg';
                    }
                }
            }
        };

        this.isCorrectAnswer = function(answerTxt) {
            return (this.isGoodAnswer == 1 &&  this.currentAnswer == answerTxt);
        };

        this.isBadAnswer = function(answerTxt) {
            return (this.isGoodAnswer == -1 &&  this.currentAnswer == answerTxt);
        };

        this.currentQuestionIndex = 0;
        this.getNextQuestion = function() {
            if(this.currentQuestionIndex < this.questions.length - 1) {
                this.currentQuestionIndex++;
                this.currentAnswer = "";
                this.checkedCurrentQuestion = false;
                this.isGoodAnswer = 0;
            }
            else {
                $('#myModal').modal('show');
            }
        };


        this.getProgress = function() {
            return ((this.currentQuestionIndex + 1)*100/this.questions.length);
        };

        this.getScore = function() {
            return "" + this.goodAnswerCounter + " / " + this.questions.length + " pkt";
        };
    }]);