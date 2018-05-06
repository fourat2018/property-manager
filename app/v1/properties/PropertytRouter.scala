package v1.properties

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Routes and URLs to the PropertyResource controller.
  */
class PropertytRouter @Inject()(controller: PropertyController) extends SimpleRouter {
  val prefix = "/v1/properties"

  def link(id: Long): String = {
    import com.netaporter.uri.dsl._
    val url = prefix / id.toString
    url.toString()
  }

  override def routes: Routes = {




  }

}
