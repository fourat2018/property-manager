package v1.properties.forms

import play.api.data.format.Formats
import java.sql.Date





case class PriceFormInput(price:Double, date: Date)



object PriceFormInput {

  import play.api.data.Forms._
  import play.api.data.Form

   val priceForm: Form[PriceFormInput] = {


    Form(
      mapping(
        "price" -> of(Formats.doubleFormat),
        "date" -> of(Formats.sqlDateFormat("yyyy-MM-dd"))

      )(PriceFormInput.apply)(PriceFormInput.unapply)
    )
  }

}

