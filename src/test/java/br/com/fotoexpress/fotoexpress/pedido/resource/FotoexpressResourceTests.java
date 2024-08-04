package br.com.fotoexpress.fotoexpress.pedido.resource;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FotoexpressResourceTests {



	@BeforeAll
	public static void setBaseUri() {
		RestAssured.baseURI = "http://localhost:8080/";
	}


}
