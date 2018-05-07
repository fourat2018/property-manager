package v1.properties

import javax.inject.Inject

import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._

import v1.properties.forms.PropertyFormInput

import scala.concurrent.{ExecutionContext, Future}






/**
  * Takes HTTP requests and produces JSON.
  */
class PropertyController @Inject()(cc: PropertyControllerComponents)(implicit ec: ExecutionContext)
    extends PropertyBaseController(cc) {

  private val logger = Logger(getClass)


  def createProperty: Action[AnyContent] = PropertyAction.async { implicit request =>
    logger.trace("new property creation request: ")
    processCreateProperty()
  }

  def retrieveProperty(propertyId: Long): Action[AnyContent] = PropertyAction.async { implicit request =>
    logger.trace(s"retrieve property : id = $propertyId")
    propertyResourceHandler.propertyLookup(propertyId).map { property =>Ok(Json.toJson(property))}
  }

  private def processCreateProperty[A]()(implicit request: PropertyRequest[A]): Future[Result] = {
    def failure(badForm: Form[PropertyFormInput]) = {
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(input: PropertyFormInput) = {
      propertyResourceHandler.addProperty(input).map { property =>
        Created(Json.toJson(property))
      }
    }

    PropertyFormInput.propertyForm.bindFromRequest().fold(failure, success)
  }

  }







