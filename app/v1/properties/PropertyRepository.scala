package v1.properties

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext
import play.api.{Logger, MarkerContext}
import play.api.db.slick.DatabaseConfigProvider
import java.sql.Date

import slick.jdbc.JdbcProfile


import v1.properties.forms.PropertyFormInput



final case class PropertyData(id: Long, address: String, postCode: String,latitude:Double,longitude:Double,bedroomCount:Option[Int],surface:Option[Double])



class PropertyExecutionContext @Inject()(actorSystem: ActorSystem) extends CustomExecutionContext(actorSystem, "repository.dispatcher")



trait PropertyRepository {

//  def createProperty(data: PropertyFormInput)(implicit mc: MarkerContext): Future[PropertyData]
//
//  def listProperties()(implicit mc: MarkerContext): Future[Seq[PropertyData]]
//
//  def getProperty(id: Long)(implicit mc: MarkerContext): Future[Option[PropertyData]]
//
//  def deleteProperty(id:Long) (implicit mc: MarkerContext):Future[Int]
//
//  def updateProperty(id:Long,data: PropertyFormInput)(implicit mc: MarkerContext): Future[PropertyData]


}


@Singleton
class PropertyRepositoryImpl @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: PropertyExecutionContext) extends PropertyRepository {

  private val logger = Logger(this.getClass)

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._


  private class PropertyTable(tag: Tag) extends Table[PropertyData](tag, "properties") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def address = column[String]("address")

    def postcode = column[String]("postcode")

    def latitude = column[Double]("latitude")

    def longitude = column[Double]("longitude")

    def bedroomCount = column[Option[Int]]("bedroom_count")

    def surface = column[Option[Double]]{"surface"}



    def * = (id, address, postcode,latitude,longitude,bedroomCount,surface) <> ((PropertyData.apply _).tupled, PropertyData.unapply)
  }





}