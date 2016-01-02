package pl.bielak.quiz.backend.utils

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

public class ServiceResponses {

  def static createResponse(status, body) {
    Response.status(status).entity(body)
      .header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
      .type(MediaType.APPLICATION_JSON).build()
  }

  def static BLANK_ARGUMENTS_RESPONSE() {
    createResponse(Response.Status.BAD_REQUEST, ["Message" : "Arguments mustn\'t be blank!"])
  }

  def static ILLEGAL_JSON_RESPONSE() {
    createResponse(Response.Status.BAD_REQUEST, ["Message" : "Illegal JSON format!"])
  }

  def static GIVEN_QUIZ_DOESNT_EXISTS_RESPONSE() {
    createResponse(Response.Status.NOT_FOUND, ["Message" : "Given quiz doesn\'t exists!"])
  }

  def static OK() {
    Response.ok().build()
  }

  def static Response OK_RESPONSE_WITH_BODY(json) {
    createResponse(Response.Status.OK, json)
  }

}
