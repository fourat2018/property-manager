package v1.properties

import scala.concurrent.{ExecutionContext, Future}

import play.api.MarkerContext


import javax.inject.{Inject, Provider}

import v1.properties.forms.PropertyFormInput
import v1.properties.resource._


class PropertyResourceHandler @Inject()(
    routerProvider: Provider[PropertyRouter],
    propertyRepository: PropertyRepository)(implicit ec: ExecutionContext) {

  def addProperty(propertyInput: PropertyFormInput)(implicit mc: MarkerContext): Future[PropertyResource] = {
    propertyRepository.insertProperty(propertyInput).map { prop => createPropertyResource(prop)}
  }


  def propertyLookup(propertyId: Long)(implicit mc: MarkerContext): Future[Option[PropertyResource]] = {
    val propertyFuture = propertyRepository.getProperty(propertyId)
    propertyFuture.map { maybePropertyData =>
      maybePropertyData.map { propertyData =>
        createPropertyResource( propertyData)
      }
    }
  }

  def propertyDelete(propertyId: Long)(implicit mc: MarkerContext)= {
    propertyRepository.deleteProperty(propertyId)
  }







  private def createPropertyResource(p: PropertyData): PropertyResource = {
    PropertyResource(p.id, p.address, p.postCode,p.latitude,p.longitude,p.bedroomCount,p.surface,routerProvider.get.link(p.id))
  }












}
