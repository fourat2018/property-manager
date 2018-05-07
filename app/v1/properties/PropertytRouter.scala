package v1.properties

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Routes and URLs to the PropertyResource controller.
  */
class PropertyRouter @Inject()(controller: PropertyController) extends SimpleRouter {
  val prefix = "/v1/properties"

  def link(id: Long): String = {
    import com.netaporter.uri.dsl._
    val url = prefix / id.toString
    url.toString()
  }

  override def routes: Routes = {

    //properties API
    case GET(p"/") =>
      controller.listProperties()

    case POST(p"/") =>
      controller.createProperty()

    case GET(p"/$propertyId") =>
      controller.retrieveProperty(propertyId.toLong)

    case DELETE(p"/$propertyId") =>
      controller.removeProperty(propertyId.toLong)

    case PATCH((p"/$propertyId")) =>
      controller.updateProperty(propertyId.toLong)
//
//
//    //Price Resource API
    case GET(p"/$propertyId/prices" ) =>
      controller.listPropertyPrices(propertyId.toLong)

    case POST(p"/$propertyId/prices") =>
      controller.addPropertyPrice(propertyId.toLong)



  }

}
