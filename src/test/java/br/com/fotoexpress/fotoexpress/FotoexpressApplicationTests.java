package br.com.fotoexpress.fotoexpress;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
class FotoexpressApplicationTests {

	@BeforeAll
	public static void setBaseUri() {
		RestAssured.baseURI = "http://localhost:8080/"; // URL base a ser utilizada em todos os testes
	}

//	@Test
//	public void testaRetornoSucessoEndpointListaPacotes() {
//		given()
//				.when()
//				.get("pedido/pacotes")
//				.then()
//				.statusCode(200)
//		;
//	}

}
