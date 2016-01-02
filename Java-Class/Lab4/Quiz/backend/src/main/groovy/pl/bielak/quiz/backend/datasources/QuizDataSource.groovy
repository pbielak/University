package pl.bielak.quiz.backend.datasources

import com.mongodb.DBCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class QuizDataSource {

  @Autowired
  @Qualifier("quizCollection")
  DBCollection quiz

  def getAllQuizzes() {
    def allQuizzes = quiz.find().toArray()
    allQuizzes.each {it.removeField("_id")}
    return allQuizzes
  }
}
