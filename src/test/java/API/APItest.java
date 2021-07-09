package API;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import API.pojos.*;
import groovy.json.JsonBuilder;
import groovy.json.JsonToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class APItest {
    @Test
    public void FirstTest() {
        AuthCreateTokenPojo authCreateTokenPojo = new AuthCreateTokenPojo();
        authCreateTokenPojo.setUsername("admin");
        authCreateTokenPojo.setPassword("password123");
        TokenPojo Auth = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(authCreateTokenPojo)
                .when()
                .post()
                .then()
                .statusCode(200).extract().as(TokenPojo.class);

        BookingCreateRequestPojo bookingCreateRequestPojo = new BookingCreateRequestPojo();
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        bookingCreateRequestPojo.setFirstname("1");
        bookingCreateRequestPojo.setLastname("2");
        bookingCreateRequestPojo.setTotalprice(1600);
        bookingCreateRequestPojo.setDepositpaid(true);
        bookingCreateRequestPojo.setAdditionalneeds("none");
        bookingCreateRequestPojo.setBookingdates(bookingdates);


        BookingCreateBookingResponsePojo res = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .contentType(ContentType.JSON)
                .body(bookingCreateRequestPojo)
                .post()
                .then().extract().as(BookingCreateBookingResponsePojo.class);
        int createdBookingId = res.getBookingid();

        String token =Auth.getToken();
        token="token="+token;
        System.out.println(token);
        RequestSpecification REQ_SPEC = new RequestSpecBuilder().addCookie(token).build();
        Cookie cookie2 = new Cookie.Builder(token).build();
        given().baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/"+createdBookingId)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .cookie(cookie2)
                .body(bookingCreateRequestPojo)
                .when()
                .put()
                .then().statusCode(200);
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/"+createdBookingId)
                .contentType(ContentType.JSON)
                .cookie(token)
                .when()
                .delete();
    }


}


