package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello(name: String) = Action {
    Ok("Hello " + name)
  }

  def gotIt = Action { implicit request =>
    Ok("Got request [" + request + "]")
  }

  def parseJson = Action(parse.json) { implicit request =>
    Ok("Got request [" + request + "]")
  }

  def notFound = Action{
    NotFound
  }
  def pageNotFound = Action {
    NotFound(<h1>Page not found</h1>)
  }
  def badRequest = Action{
    BadRequest
  }
  def oops = Action{
    InternalServerError("Oops")
  }
  def strange = Action{
    Status(488)("String response type")
  }

  def redirect = Action {
    Redirect("/user/home")
  }

  def todo(namd: String) = TODO
}