//package org.example.chatgptmovierecomendations.controller;
//
//import org.example.chatgptmovierecomendations.model.OMDBResponse;
//import org.example.chatgptmovierecomendations.service.OMDBService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/movie-info")
//public class OMDBController {
//
//    private final OMDBService omdbService;
//
//    public OMDBController(OMDBService omdbService) {
//        this.omdbService = omdbService;
//    }
//
//    @GetMapping()
//    public OMDBResponse getMovieInfo(@RequestParam String searchParam) {
//        System.out.println("CONRTROLLER: get movie info accessed");
//        return omdbService.movieInfoRequest(searchParam);
//    }
//}
