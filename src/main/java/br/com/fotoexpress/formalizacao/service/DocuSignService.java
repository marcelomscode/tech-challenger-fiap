package br.com.fotoexpress.formalizacao.service;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth;
import com.docusign.esign.model.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class DocuSignService {

  @Value("${docusign.integrationKey}")
  private String integrationKey;

  @Value("${docusign.userId}")
  private String userId;

  @Value("${docusign.apiBaseUrl}")
  private String apiBaseUrl;

  @Value("${docusign.accountId}")
  private String accountId;

  @Value("${docusign.assinaturaContratoURL}")
  private String assinaturaContratoURL;

  private ApiClient apiClient;

  private void init() throws ApiException, IOException {
    apiClient = new ApiClient(apiBaseUrl);

    ClassPathResource classPathResource = new ClassPathResource("privateKey.txt");
    Path privateKey = classPathResource.getFile().toPath();

    byte[] privateKeyBytes = Files.readAllBytes(privateKey);
    OAuth.OAuthToken oAuthToken =
        apiClient.requestJWTUserToken(
            integrationKey, userId, Arrays.asList(OAuth.Scope_SIGNATURE), privateKeyBytes, 3600);

    apiClient.setAccessToken(oAuthToken.getAccessToken(), oAuthToken.getExpiresIn());
  }

  public String sendEnvelope(String signerEmail, String signerName, byte[] documentBytes)
      throws ApiException, IOException {
    init();

    EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
    envelopeDefinition.setEmailSubject("Please sign this document");

    Document document = new Document();
    document.setDocumentBase64(Base64.getEncoder().encodeToString(documentBytes));
    document.setName("Example Document");
    document.setFileExtension("pdf");
    document.setDocumentId("1");

    envelopeDefinition.setDocuments(Arrays.asList(document));

    Signer signer = new Signer();
    signer.setEmail(signerEmail);
    signer.setName(signerName);
    signer.setRecipientId("1");

    SignHere signHere = new SignHere();
    signHere.setAnchorString("/sn1/");
    signHere.setAnchorYOffset("10");
    signHere.setAnchorUnits("pixels");
    signHere.setAnchorXOffset("20");

    Tabs tabs = new Tabs();
    tabs.setSignHereTabs(Arrays.asList(signHere));
    signer.setTabs(tabs);

    Recipients recipients = new Recipients();
    recipients.setSigners(Arrays.asList(signer));
    envelopeDefinition.setRecipients(recipients);

    envelopeDefinition.setStatus("sent");

    EventNotification eventNotification = new EventNotification();
    eventNotification.setUrl(assinaturaContratoURL);
    eventNotification.setLoggingEnabled("true");
    EnvelopeEvent completedEvent = new EnvelopeEvent();
    completedEvent.setEnvelopeEventStatusCode("completed");
    eventNotification.setEnvelopeEvents(Arrays.asList(completedEvent));

    envelopeDefinition.setEventNotification(eventNotification);

    EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
    EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envelopeDefinition);
    return envelopeSummary.getEnvelopeId();
  }
}
