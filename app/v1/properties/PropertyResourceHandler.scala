package v1.properties

import javax.inject.{Inject, Provider}

import play.api.MarkerContext

import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._






class PropertyResourceHandler @Inject()(
    routerProvider: Provider[PropertyRouter],
    propertyRepository: PropertyRepository)(implicit ec: ExecutionContext) {


}
