package org.example.chatgptmovierecomendations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
//
public class ChatGPTResponse {

    private String answer;
    private List<Map<String, String>> messages;

    //
    public ChatGPTResponse(String answer) {
        this.answer = answer;
    }
}
