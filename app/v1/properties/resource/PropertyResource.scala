package  v1.properties.resource


import play.api.libs.json._
import play.api.libs.functional.syntax._


/*
* Json mapping of the resource sent in the response
*
 */

case class PropertyResource(id: Long, address:String, postcode:String, latitude:Double,longitude:Double, bedroomCount:Option[Int],surface:Option[Double], link:String)

object PropertyResource {


  implicit val implicitWrites: Writes[PropertyResource] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "address").write[String] and
      (JsPath \ "postcode").write[String] and
      (JsPath \ "latitude").write[Double] and
      (JsPath \ "longitude").write[Double] and
      {JsPath \ "bedroom_count"}.writeNullable[Int] and
      {JsPath \ "surface"}.writeNullable[Double] and
      (JsPath \ "link").write[String]
    )(unlift(PropertyResource.unapply))
}


