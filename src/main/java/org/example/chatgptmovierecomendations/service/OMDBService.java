package org.example.chatgptmovierecomendations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chatgptmovierecomendations.model.OMDBCompletionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class OMDBService {

    @Value("${app.url.omdb}")
    private String omdbURL;

    @Value("${app.api-key.omdb}")
    private String omdb_KEY;

    private WebClient webClient;
    private final ObjectMapper objectMapper;

    public OMDBService(ObjectMapper objectMapper) {
        this.webClient = WebClient.create();
        this.objectMapper = objectMapper;
    }

    public OMDBCompletionResponse movieInfoRequest(String searchParam) {
        String titleNoSpaces = searchParam; //.replace(" ", "%20");
        System.out.println("Title no spaces: " + titleNoSpaces);
        String url = omdbURL + "?apikey=" + omdb_KEY + "&t=" + titleNoSpaces; // WORKS
        String json = "";

        try {
            Mono<String> responseMono = webClient.get()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);

            String responseBody = responseMono.block();
            System.out.println(responseBody);

//            if (responseBody != null && responseBody.contains("\"Response\":\"False\"")) {
//                System.out.println("Movie not found!");
//                return null;
//            }

                OMDBCompletionResponse omdbResponse = objectMapper.readValue(responseBody, OMDBCompletionResponse.class);
                return omdbResponse;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }
}
