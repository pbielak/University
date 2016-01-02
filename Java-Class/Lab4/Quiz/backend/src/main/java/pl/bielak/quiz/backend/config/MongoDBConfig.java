package pl.bielak.quiz.backend.config;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "pl.bielak.quiz.*")
public class MongoDBConfig extends AbstractMongoConfiguration {
  private static final String DATABASE_NAME = "bielak";
  private static final String DATABASE_HOST = "localhost";
  private static final int DATABASE_PORT = 27017;

  private static final String USERNAME = "";
  private static final String PASSWORD = "";

  private static final String QUESTIONS_COLLECTION_NAME = "Questions";
  private static final String QUIZ_COLLECTION_NAME = "Quiz";



  @Override
  protected String getDatabaseName() {
    return DATABASE_NAME;
  }

  @Bean
  @Override
  public Mongo mongo() throws Exception {
    MongoClient mongoClient = new MongoClient(DATABASE_HOST, DATABASE_PORT);
    mongoClient.setWriteConcern(WriteConcern.SAFE);
    return mongoClient;
  }

  @Bean
  public MongoDbFactory mongoDbFactory() throws Exception {
    UserCredentials userCredentials = new UserCredentials(USERNAME, PASSWORD);
    return new SimpleMongoDbFactory(mongo(), DATABASE_NAME, userCredentials);
  }

  @Bean(name = "mongoTemplate")
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory());
  }

  @Bean(name = "questionsCollection")
  public DBCollection questionCollection() throws Exception {
    MongoTemplate mongoTemplate = mongoTemplate();
    return mongoTemplate.getCollection(QUESTIONS_COLLECTION_NAME);
  }

  @Bean(name = "quizCollection")
  public DBCollection quizCollection() throws Exception {
    MongoTemplate mongoTemplate = mongoTemplate();
    return mongoTemplate.getCollection(QUIZ_COLLECTION_NAME);
  }

}
