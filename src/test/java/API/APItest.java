package API;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import API.pojos.*;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

public class APItest {
    @Test
    public void FirstTest() {
        String login = "admin";
        String password = "password123";
        String checkin = "2018-01-01";
        String checkout = "2019-01-01";
        String firstName = "Bill";
        String lastName = "Gates";
        int totalPrice = 1000;
        boolean depositPaid = true;
        String additionalNeeds = "none";

        AuthCreateTokenPojo authCreateTokenPojo = new AuthCreateTokenPojo();
        authCreateTokenPojo.setUserName(login);
        authCreateTokenPojo.setPassword(password);

        TokenPojo Auth = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(authCreateTokenPojo)
                .when()
                .post()
                .then()
                .statusCode(200).extract().as(TokenPojo.class);
        String token = "token=" + Auth.getToken();

        BookingCreateRequestPojo bookingCreateRequestPojo = new BookingCreateRequestPojo();
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        bookingCreateRequestPojo.setFirstname(firstName);
        bookingCreateRequestPojo.setLastname(lastName);
        bookingCreateRequestPojo.setTotalprice(totalPrice);
        bookingCreateRequestPojo.setDepositpaid(depositPaid);
        bookingCreateRequestPojo.setAdditionalneeds(additionalNeeds);
        bookingCreateRequestPojo.setBookingdates(bookingdates);

        BookingCreateBookingResponsePojo res = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .contentType(ContentType.JSON)
                .body(bookingCreateRequestPojo)
                .post()
                .then().statusCode(200).extract().as(BookingCreateBookingResponsePojo.class);
        int createdBookingId = res.getBookingid();

        GetBookingResponsePojo createdUser = given().baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/"+createdBookingId)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().as(GetBookingResponsePojo.class);

        Assert.assertEquals(firstName, createdUser.getFirstname());
        Assert.assertEquals(lastName, createdUser.getLastname());
        Assert.assertEquals(checkin, createdUser.getBookingdates().getCheckin());
        Assert.assertEquals(checkout, createdUser.getBookingdates().getCheckout());
        Assert.assertEquals(additionalNeeds, createdUser.getAdditionalneeds());
        Assert.assertEquals(depositPaid, createdUser.isDepositpaid());
        Assert.assertEquals(totalPrice, createdUser.getTotalprice());

        BookingCreateRequestPojo update = new BookingCreateRequestPojo();
        update= bookingCreateRequestPojo;
        update.setTotalprice(10);

        given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/"+createdBookingId)
                .contentType(ContentType.JSON)
                //.accept(ContentType.JSON)
                .header("Cookie", token)
                .body(update)
                .when()
                .patch()
                .then()
                .statusCode(200);

        given().baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/"+createdBookingId)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("totalprice", equalTo(10));

        Assert.assertTrue(given().baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("bookingid").contains(createdBookingId));

        given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking/"+createdBookingId)
                .contentType(ContentType.JSON)
                .header("Cookie", token)
                .when()
                .delete().then().statusCode(201);
    }
}


