package  v1.properties.resource


import play.api.libs.json._
import play.api.libs.functional.syntax._





case class PricesResource(prices :Seq[PriceResource], property:String)

case class PriceResource(price:Double,date:String)


object PricesResource {

  implicit val implicitWrites : Writes[PricesResource] = (
    (JsPath \ "price_evolution").write[Seq[PriceResource]] and
      (JsPath \ "property").write[String]
    )(unlift(PricesResource.unapply))

}

object PriceResource {
  implicit val implicitWrites : Writes[PriceResource] = (
    (JsPath \ "price").write[Double] and
      (JsPath \ "date").write[String]
    )(unlift(PriceResource.unapply))


}