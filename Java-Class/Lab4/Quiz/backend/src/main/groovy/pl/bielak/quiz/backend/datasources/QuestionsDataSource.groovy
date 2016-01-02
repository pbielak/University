package pl.bielak.quiz.backend.datasources

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class QuestionsDataSource {

  @Autowired
  @Qualifier("questionsCollection")
  DBCollection questions


  def getAllQuestions() {
    def allQuestions = questions.find().toArray()
    allQuestions.each {it.removeField("_id")}
    return allQuestions
  }

  def getQuestionsFromQuiz(int quizId) {
    def query = (BasicDBObject) ["quiz_id" : (quizId)]
    def questions = questions.find(query).toArray()
    questions?.each { it.removeField("_id") }
    return questions
  }

  boolean checkIfQuizExists(int quizId) {
    def query = (BasicDBObject) ["quiz_id" : (quizId)]
    return questions.findOne(query) != null
  }
}
