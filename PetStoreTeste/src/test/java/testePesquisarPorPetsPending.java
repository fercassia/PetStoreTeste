import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class testePesquisarPorPetsPending {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "v2/";
    }

    @Test
    public void dadoProcuraPetPendente_quandoEnviarRequisicaoPending_entaoSucesso(){

        given()
                .contentType(ContentType.JSON).
        when()
                .get("pet/findByStatus?status={status}", "pending").
        then()
                .statusCode(200);
    }
}
