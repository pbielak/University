package pl.bielak.quiz.backend.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pl.bielak.quiz.backend.datasources.QuizDataSource
import pl.bielak.quiz.backend.utils.ServiceResponses

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("/quiz")
@Produces(MediaType.APPLICATION_JSON)
class Quiz {
  @Autowired
  QuizDataSource dataSource


  @GET
  @Path("")
  public Response getAllQuestions() {
    def allQuizzes = dataSource.getAllQuizzes()
    return ServiceResponses.OK_RESPONSE_WITH_BODY(allQuizzes)
  }
}
