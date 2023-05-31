package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void verifyTheTotalRecord()
    {
        response.body("size", equalTo(25));
    }
    //2. Verify the if the title of id =39305 is equal to ”Cruentus absorbeo id auctus suus cunabula sint umerus.”
    @Test
    public void  verifyTheTitleOfId39305 ()
    {
        response.body("findAll{it.id==39305}.title", hasItem("Cruentus absorbeo id auctus suus cunabula sint umerus."));
    }
    //3. Check the single user_id in the Array list ( 39304)
    @Test
    public void verifySingleUserId(){
        response.body("user_id",hasItem( 2272684));
    }
    //4. Check the multiple ids in the ArrayList (39304, 39297, 39296)
    @Test
    public void verifyMultipleUserId(){
        response.body("id", hasItems( 39304, 39297, 39296));
    }
    //5. Verify the body of userid = 2272684 is
    @Test
    public void verifybodyIsUserId(){
        response.body("find{it.user_id==2272684}.body", equalTo("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));
    }
}
