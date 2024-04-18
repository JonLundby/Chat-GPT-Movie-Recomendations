package org.example.chatgptmovierecomendations.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMDBResponse {
    private String title;
    private String year;
    private String rated;

    @Override
    public String toString() {
        return "OMDBResponse{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                '}';
    }
}
