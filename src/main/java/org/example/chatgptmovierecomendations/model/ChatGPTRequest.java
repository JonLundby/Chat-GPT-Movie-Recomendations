package org.example.chatgptmovierecomendations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// A model/entity to represent a chat gpt request
public class ChatGPTRequest {

    private String model;
    private List<Message> messages = new ArrayList<>();
    private Double temperature;
    private int max_tokens;
    private double top_p;
    private double frequency_penalty;
    private double presence_penalty;

    @Getter
    @Setter
    // A nested private static model/entity to represent a chat gpt request message ()
    public static class Message {
        private String role;
        private String content;

        // Constructor for the nested property message
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
