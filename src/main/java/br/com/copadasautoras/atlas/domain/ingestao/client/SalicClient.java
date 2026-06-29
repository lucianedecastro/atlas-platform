package br.com.copadasautoras.atlas.domain.ingestao.client;

import br.com.copadasautoras.atlas.domain.ingestao.dto.salic.SalicIncentivadorDto;
import br.com.copadasautoras.atlas.domain.ingestao.dto.salic.SalicIncentivadoresResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Component
public class SalicClient {

    private static final String BASE_URL = "https://api.salic.cultura.gov.br/api/v1/incentivadores";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<SalicIncentivadorDto> buscarPorEstado(String uf, int limite) {

        String url = BASE_URL
                + "?UF=" + URLEncoder.encode(uf, StandardCharsets.UTF_8)
                + "&sort=total_doado:desc"
                + "&limit=" + limite;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return Collections.emptyList();
            }

            SalicIncentivadoresResponse corpo =
                    objectMapper.readValue(response.body(), SalicIncentivadoresResponse.class);

            if (corpo.embedded() == null || corpo.embedded().incentivadores() == null) {
                return Collections.emptyList();
            }

            return corpo.embedded().incentivadores();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível consultar a API do SALIC agora.", e);
        }
    }

}