package pl.bielak.quiz.backend.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import pl.bielak.quiz.backend.services.Question;
import pl.bielak.quiz.backend.services.Quiz;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig{
  public JerseyConfig() {
    register(Question.class);
    register(Quiz.class);
  }
}
