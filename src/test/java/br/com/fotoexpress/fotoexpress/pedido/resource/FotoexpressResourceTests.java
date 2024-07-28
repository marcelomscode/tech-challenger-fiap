package br.com.fotoexpress.fotoexpress.pedido.resource;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
class FotoexpressResourceTests {

	@BeforeAll
	public static void setBaseUri() {
		RestAssured.baseURI = "http://localhost:8080/";
	}

	@Test
	public void testaRetornoSucessoEndpointListaPacotes() {
		given()
				.when()
				.get("pedidos/pacotes-disponiveis")
				.then()
				.log().all()
				.statusCode(200)
		;
	}

}
