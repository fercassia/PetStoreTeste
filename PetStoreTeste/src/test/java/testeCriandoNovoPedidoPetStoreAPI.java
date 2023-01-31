import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.AssertJUnit.assertEquals;

public class testeCriandoNovoPedidoPetStoreAPI {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "v2/";
    }

    private DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String shipdateAtual = dateTimeformatter.format(now);

    @Test
    public void dadoCriaNovoPedido_quandoEnviarComDadosValidos_entaoSucesso(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("id", 4);
        parametrosRequisicao.put("petId", 7);
        parametrosRequisicao.put("quantity", 7);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Aguardando Nota fiscal");
        parametrosRequisicao.put("complete", true);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).

        when().
                post("store/order").
        then().
                statusCode(200).
                body(containsString("Aguardando Nota fiscal"));
    }

    @Test
    public void dadoCriaNovoPedido_quandoEnviarSemPetId_entaoSucesso(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("id", 2);
        parametrosRequisicao.put("quantity", 3);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Aguardando pagamento");
        parametrosRequisicao.put("complete", true);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).
        when().
                post("store/order").
        then().
                statusCode(200)
                .body(containsString("Aguardando pagamento"));
    }

    @Test
    public void dadoCriaNovoPedido_quandoSemEnviarId_entaoSucesso(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("petId", 35);
        parametrosRequisicao.put("quantity", 45);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Pago");
        parametrosRequisicao.put("complete", true);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).

        when().
                post("store/order").
        then().
                statusCode(200).
                body(containsString("Pago"));
    }

    @Test
    public void dadoCriaNovoPedido_quandoRealizarPedidoComGrandeQuantidade_entaoSucesso(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("id", 10);
        parametrosRequisicao.put("petId", 11);
        parametrosRequisicao.put("quantity", 999999999);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Separando No Estoque");
        parametrosRequisicao.put("complete", true);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).

        when().
                post("store/order").
        then().
                statusCode(200).
                body(containsString("Separando No Estoque"));
    }

    @Test
    public void dadoCriaNovoPedido_quandoRealizarPedidoNaoCompleto_entaoSucesso(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("id", 98);
        parametrosRequisicao.put("petId", 224);
        parametrosRequisicao.put("quantity", 35);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Separado");
        parametrosRequisicao.put("complete", false);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).

        when().
                post("store/order").
        then().
                statusCode(200).
                body(containsString("Separado"));
    }

    @Test
    public void dadoCriaNovoPedido_quandoEnviarComDadosInvalidos_entaoFalha(){

        JSONObject parametrosRequisicao = new JSONObject();
        parametrosRequisicao.put("id", "petTeste");
        parametrosRequisicao.put("petId", "afs");
        parametrosRequisicao.put("quantity", 3);
        parametrosRequisicao.put("shipdate", shipdateAtual);
        parametrosRequisicao.put("status", "Verificando no estoque");
        parametrosRequisicao.put("complete", true);

        given()
                .body(parametrosRequisicao.toString())
                .contentType(ContentType.JSON).

        when().
                post("store/order").
        then().
                statusCode(500).
                body(containsString("something bad happened"));
    }
}
