package org.example.chatgptmovierecomendations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chatgptmovierecomendations.model.ChatGPTCompletionResponse;
import org.example.chatgptmovierecomendations.model.ChatGPTRequest;
import org.example.chatgptmovierecomendations.model.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Service
public class ChatGPTService {

    @Value("${app.api-key}")
    private String API_KEY;

    @Value("${app.url}")
    public String URL;

    @Value("${app.model}")
    public String MODEL;

    @Value("${app.temperature}")
    public double TEMPERATURE;

    @Value("${app.max_tokens}")
    public int MAX_TOKENS;

    @Value("${app.frequency_penalty}")
    public double FREQUENCY_PENALTY;

    @Value("${app.presence_penalty}")
    public double PRESENCE_PENALTY;

    @Value("${app.top_p}")
    public double TOP_P;

    // variable to append to omdb
    public String movieTitle;

    private WebClient webClient;

    public ChatGPTService() {
        this.webClient = WebClient.create();
    }

//    public ChatGPTService(WebClient webClient) {
//        this.webClient = webClient;
//    }

//    public ChatGPTResponse makeRequest(String userPrompt, String systemMessage) {
    public String makeRequest(String userPrompt, String systemMessage) {

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setModel(MODEL);
        chatGPTRequest.setTemperature(TEMPERATURE);
        chatGPTRequest.setMax_tokens(MAX_TOKENS);
        chatGPTRequest.setTop_p(TOP_P);
        chatGPTRequest.setFrequency_penalty(FREQUENCY_PENALTY);
        chatGPTRequest.setPresence_penalty(PRESENCE_PENALTY);
        chatGPTRequest.getMessages().add(new ChatGPTRequest.Message("system", systemMessage));
        chatGPTRequest.getMessages().add(new ChatGPTRequest.Message("user", userPrompt));

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        String err = "";

        try{
            json = mapper.writeValueAsString(chatGPTRequest);
//            System.out.println(json);

            ChatGPTCompletionResponse response = webClient.post()
                    .uri(new URI(URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(json))
                    .retrieve()
                    .bodyToMono(ChatGPTCompletionResponse.class)
                    .block();

            String responseMessage = response.getChoices().get(0).getMessage().getContent();
            movieTitle = responseMessage;
//            System.out.println(movieTitle);
//            return new ChatGPTResponse(responseMessage);
            return movieTitle;

        } catch (WebClientResponseException e) {
            err = "Internal server error...";
            System.out.println(e.getMessage());
            System.out.println(err);
            System.out.println("Error Message: " + e.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, err);
        } catch (Exception e) {
            err = "Internal server error...";
            System.out.println(err);
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, err);
        }


    }
}
