package br.com.fotoexpress.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FotoExpressConfig {

  @Bean
  public OpenAPI defineOpenApi() {
    Server server = new Server();
    server.setUrl("http://localhost:8080");
    server.setDescription("Desenvolvimento");

    Contact myContact = new Contact();
    myContact.setName("ALUNOS-5ADJT-FIAP");

    Info information =
        new Info()
            .title("Tech Challenger API")
            .version("1.0")
            .description("Esta API expoe endpoints para gerenciar pedidos de fotos.")
            .contact(myContact);
    return new OpenAPI().info(information).servers(List.of(server));
  }
}
