import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

class PropertyControllerSpec extends PlaySpec with GuiceOneAppPerTest {





  "PropertyControllerSpec" should {

    "Not accept a CREATE query with a missing field" in {
      val request = FakeRequest(POST, "/v1/properties").withHeaders(HOST -> "localhost:9000").withFormUrlEncodedBody(("address","toto"),("postcode","titi"),("latitude","12345.25"))
      val home = route(app, request).get

      contentAsString(home) must include ("This field is required")
    }


    "accept a CREATE query with all the required fields" in {
      val request = FakeRequest(POST, "/v1/properties").withHeaders(HOST -> "localhost:9000")
        .withFormUrlEncodedBody(("address","toto"),("postcode","titi"),("latitude","12345.25"),("longitude","0"))
      val home = route(app, request).get

      contentAsString(home) must include ("/v1/properties/1\"")
    }




  }

}