package pl.bielak.quiz.backend.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pl.bielak.quiz.backend.datasources.QuestionsDataSource
import pl.bielak.quiz.backend.utils.ServiceResponses

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("/question")
@Produces(MediaType.APPLICATION_JSON)
public class Question {

  @Autowired
  QuestionsDataSource dataSource

  @GET
  @Path("")
  public Response getAllQuestions() {
    def allQuestions = dataSource.getAllQuestions()
    return ServiceResponses.OK_RESPONSE_WITH_BODY(allQuestions)
  }

  @GET
  @Path("/{quizId}")
  public Response getAllQuestionsFromQuiz(@PathParam("quizId") int quizId) {
    if(!dataSource.checkIfQuizExists(quizId)) {
      return ServiceResponses.GIVEN_QUIZ_DOESNT_EXISTS_RESPONSE()
    }

    def questions = dataSource.getQuestionsFromQuiz(quizId)
    return ServiceResponses.OK_RESPONSE_WITH_BODY(questions)
  }
}
