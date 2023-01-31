import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;


public class testePesquisarPorPets {


   @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "v2/";
    }

    @Test
    public void dadoQuProcuraPet_quandoProcuraPorUmPetCadastrado_entaoPetEncontrado(){
        given()
                .contentType(ContentType.JSON).
        when().
                get("pet/{id}", 2).
        then().
                statusCode(200).
                body("id", equalTo(2));
    }
    @Test
    public void dadoQuProcuraPet_quandoProcuraPorUmPetNaoCadastrado_entaoPetNaoEncontrado(){
        given()
                .contentType(ContentType.JSON).
        when().
                get("pet/{id}", 6).
        then().
                statusCode(404).
                body("message", equalTo("Pet not found"));
    }

    @Test
    public void dadoQuProcuraPet_quandoProcuraPorUmPetInvalido_entaoFalha(){
        given()
                .contentType(ContentType.JSON).
                when().
                get("pet/{id}", "biggle").
                then().
                statusCode(404).
                body("type", equalTo("unknown"));
    }
}
