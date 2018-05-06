package v1.properties.forms

import play.api.data.format.Formats



case class PropertyFormInput(address: String, postcode: String,latitude:Double,longitude:Double, bedroomCount:Option[Int],surface : Option[Double])

object PropertyFormInput {

  import play.api.data.Forms._
  import play.api.data.Form



  val propertyForm: Form[PropertyFormInput] = {

  //Data provided by the client in case of creation or update of a property (POST/PATCH)
    Form(
      mapping(
        "address" -> nonEmptyText,
        "postcode" -> nonEmptyText,
        "latitude" -> of(Formats.doubleFormat),
        "longitude" -> of(Formats.doubleFormat),
        "bedroom_count" -> optional(number),
        "surface" -> optional(of(Formats.doubleFormat))
      )(PropertyFormInput.apply)(PropertyFormInput.unapply)
    )
  }


}