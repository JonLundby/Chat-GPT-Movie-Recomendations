package org.example.chatgptmovierecomendations.controller;

import org.example.chatgptmovierecomendations.model.OMDBCompletionResponse;
import org.example.chatgptmovierecomendations.service.ChatGPTService;
import org.example.chatgptmovierecomendations.service.OMDBService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "*")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;
    private final OMDBService omdbService;

    final static String SYSTEM_MSG = "You are a helpful assistant that gives movie recommendations based on input from a user. " +
            "You will answer with nothing but the title of a movie that relates to the given input from the user. " +
            "If too little information is given then just find a movie based on whatever information is given";

    public ChatGPTController(ChatGPTService chatGPTService, OMDBService omdbService) {
        this.chatGPTService = chatGPTService;
        this.omdbService = omdbService;
    }

    @GetMapping
    public OMDBCompletionResponse getMovieRecommendation(@RequestParam String topic) {
        System.out.println("Topic: " + topic);
        String movieTitle = chatGPTService.makeRequest(topic, SYSTEM_MSG);
        System.out.println("Title from gpt: " + movieTitle);
        return omdbService.movieInfoRequest(movieTitle);
    }
}