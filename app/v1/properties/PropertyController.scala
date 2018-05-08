package v1.properties

import javax.inject.Inject

import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._

import v1.properties.forms._

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

  def retrieveProperty(propertyId: Long): Action[AnyContent] = PropertyAction.async {
    logger.trace(s"retrieve property : id = $propertyId")
    propertyResourceHandler.propertyLookup(propertyId).map { property =>Ok(Json.toJson(property))}
  }

  def removeProperty(propertyId:Long): Action[AnyContent] = PropertyAction.async {
    logger.trace(s"remove property : id = $propertyId ")
    propertyResourceHandler.propertyDelete(propertyId).map { status => Ok(""+status)
    }
  }

  def listProperties()  : Action[AnyContent] = PropertyAction.async {
    logger.trace("list properties ")
    propertyResourceHandler.propertiesList.map { properties =>Ok(Json.toJson(properties))}
  }


  def updateProperty(propertyId:Long) : Action[AnyContent] = PropertyAction.async { implicit request =>
    logger.trace(s"update property : id = $propertyId ")
    processUpdateProperty(propertyId)
  }

  //***************Prices*************************************

  def addPropertyPrice(propertyId:Long): Action[AnyContent] = PropertyAction.async { implicit request =>
    logger.trace(s"add Price to the property: id = $propertyId")
    processAddPropertyPrice(propertyId)
  }

  def listPropertyPrices(propertyId:Long ) : Action[AnyContent] = PropertyAction.async {
    logger.trace(s"get prices evolution of the property: id = $propertyId")
    propertyResourceHandler.getPropertyPrices(propertyId).map(prices =>Ok(Json.toJson(prices) ))
  }

  //***********Forms mapping handlers ***************************

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

  private def processUpdateProperty[A](propertyId:Long)(implicit request: PropertyRequest[A]): Future[Result] = {
    def failure(badForm: Form[PropertyFormInput]) = {
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(propertyId:Long,input: PropertyFormInput) = {
      propertyResourceHandler.updateProperty(propertyId,input).map { status =>
        Accepted(Json.toJson(status))
      }
    }

    PropertyFormInput.propertyForm.bindFromRequest().fold(failure, aProperty=>success(propertyId,aProperty))
  }


  private def processAddPropertyPrice[A](propertyId:Long)(implicit request: PropertyRequest[A]): Future[Result] = {
    def failure(badForm: Form[PriceFormInput]) = {
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(propertyId:Long,input: PriceFormInput) = {
      propertyResourceHandler.addPropertyPrice(propertyId,input).map { status =>
        Accepted(Json.toJson(status))
      }
    }

    PriceFormInput.priceForm.bindFromRequest().fold(formWithErrors =>failure(formWithErrors), aPriceForm => success(propertyId,aPriceForm))
  }


  //  private def processReturnStatus(status : Int) : Future[Result] = status match {
//    case 0 => Future(BadRequest(Json.toJson(status)))
//    case _    => Future(Accepted(Json.toJson(status)))
//  }

  }







