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
      val request1 = FakeRequest(POST, "/v1/properties").withHeaders(HOST -> "localhost:9000")
        .withFormUrlEncodedBody(("address","toto"),("postcode","titi"),("latitude","12345.25"),("longitude","0"))
      val home1 = route(app, request1).get

      contentAsString(home1) must include ("/v1/properties/1\"")


      val request2 = FakeRequest(POST, "/v1/properties").withHeaders(HOST -> "localhost:9000")
        .withFormUrlEncodedBody(("address","toto"),("postcode","titi"),("latitude","12345.25"),("longitude","0"))
      val home2 = route(app, request2).get

      contentAsString(home2) must include ("/v1/properties/2\"")


      val request3 = FakeRequest(POST, "/v1/properties").withHeaders(HOST -> "localhost:9000")
        .withFormUrlEncodedBody(("address","toto"),("postcode","titi"),("latitude","12345.25"),("longitude","0"))
      val home3 = route(app, request3).get

      contentAsString(home3) must include ("/v1/properties/3\"")

    }


    "return a created property" in {
      val request = FakeRequest(GET, "/v1/properties/1").withHeaders(HOST -> "localhost:9000")
      val home = route(app, request).get

      contentAsString(home) must include ("/v1/properties/1\"")
    }

    "return null in case a requested query does not exist" in {
      val request = FakeRequest(GET, "/v1/properties/125").withHeaders(HOST -> "localhost:9000")
      val home = route(app, request).get

      contentAsString(home) must include ("null")
    }


    "accept a delete on a property that exists " in {
      val request = FakeRequest(DELETE, "/v1/properties/1").withHeaders(HOST -> "localhost:9000")
      val home = route(app, request).get

      contentAsString(home) must include ("1")

    }

    "Ensure this property does not exists anymore" in {
      val request = FakeRequest(GET, "/v1/properties/1").withHeaders(HOST -> "localhost:9000")
      val home = route(app, request).get

      contentAsString(home) must include ("null")
    }


    "reject a delete on a property that does not exist " in {
      val request = FakeRequest(DELETE, "/v1/properties/1").withHeaders(HOST -> "localhost:9000")
      val home = route(app, request).get

      contentAsString(home) must include ("0")

    }













  }

}