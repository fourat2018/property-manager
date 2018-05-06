package v1.properties

import scala.concurrent.{ExecutionContext, Future}

import play.api.MarkerContext


import javax.inject.{Inject, Provider}

import v1.properties.forms.PropertyFormInput








class PropertyResourceHandler @Inject()(
    routerProvider: Provider[PropertyRouter],
    propertyRepository: PropertyRepository)(implicit ec: ExecutionContext) {

  def createProperty(propertyInput: PropertyFormInput)(implicit mc: MarkerContext): Future[Int] = {
    Future{1}
  }


}
