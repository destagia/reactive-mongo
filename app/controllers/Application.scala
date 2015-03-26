package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

import jp.kamasu.util._

object Application extends Controller {

    def index = Action {
        Ok(views.html.index("Your new application is ready."))
    }

    val form = Form(tuple("id" -> text, "age" -> number))
    def insertData = Action.async { request =>
        form.bindFromRequest()(request).value match {
            case Some((id, age)) =>
                MongoDB.insert(id, age).map(_ match {
                    case Inserted() => Ok("insert!")
                    case Duplecated() => BadRequest("idExisted")
                    case Error(status) => InternalServerError(status)
                })
            case None =>
                Future(BadRequest("parameterMissing"))
        }
    }

}