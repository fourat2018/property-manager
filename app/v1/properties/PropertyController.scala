package v1.properties

import javax.inject.Inject

import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


/**
  * Takes HTTP requests and produces JSON.
  */
class PropertyController @Inject()(cc: PropertyControllerComponents)(implicit ec: ExecutionContext)
    extends PropertyBaseController(cc) {

  private val logger = Logger(getClass)


}
