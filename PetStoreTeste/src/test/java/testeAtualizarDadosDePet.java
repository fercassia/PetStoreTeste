import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class testeAtualizarDadosDePet {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "v2/";
    }

    @Test
    public void dadoAtualizaDadosPet_quandoEnviarDadosValidos_entaoSucesso(){
        String corpoDaRequisicao = "{\n" +
                "  \"id\": 2,\n" +
                "  \"category\": {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Silvestre\"\n" +
                "  },\n" +
                "  \"name\": \"Rick\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://1.bp.blogspot.com/_5CjSK4uq6KE/S6-DjNEXroI/AAAAAAAAAEc/YjQeUnYlHP0/s1600/Passaro_diamente-gold.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"Passaro da Cabeça vermelha\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"Disponível\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(corpoDaRequisicao).
        when()
                .put("pet").
        then()
                .statusCode(200)
                .body("name", equalTo("Rick"));
    }

    @Test
    public void dadoAtualizaDadosPet_quandoEnviarComMaisDeUmaTag_entaoSucesso(){
        String corpoDaRequisicao = "{\n" +
                "  \"id\": 59,\n" +
                "  \"category\": {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Silvestre\"\n" +
                "  },\n" +
                "  \"name\": \"CabecinhaAmarela\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://s3.amazonaws.com/media.wikiaves.com.br/images/7591/1957877g_32c4970ca5bb1273588ac0df4d5de88c.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"name\": \"Passaro da Cabeça amarela\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"name\": \"PicaPau\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"Passaro do topete\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"Indisponivel\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(corpoDaRequisicao).
        when()
                .put("pet").
        then()
                .statusCode(200)
                .body("id", equalTo(59));
    }

    @Test
    public void dadoAtualizaDadosPet_quandoAtualizarApenasOStatus_entaoSucesso(){
        String corpoDaRequisicao = "{\n" +
                "  \"id\": 59,\n" +
                "  \"photoUrls\": [],\n" +
                "  \"tags\": [],\n" +
                "  \"status\": \"Disponivel\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(corpoDaRequisicao).
        when()
                .put("pet").
        then()
                .statusCode(200)
                .body("status", equalTo("Disponivel"));
    }
}