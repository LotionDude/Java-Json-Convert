package org.lotan.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.request.PostInformationRequest;
import models.request.PostRequest;
import models.request.UserRequest;
import models.response.UserResponse;
import org.lotan.utility.convert.JsonNodeConverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode request = objectMapper.readTree(new File("./src/main/resources/request.json"));
        JsonNode response = objectMapper.readTree(new File("./src/main/resources/response.json"));

        JsonNodeConverter jsonNodeConverter = new JsonNodeConverter(request, response, objectMapper);

        UserRequest userRequest = UserRequest.builder()
                .username("my_username")
                .password("my_password")
                .posts(List.of(
                        PostRequest.builder()
                                .info(PostInformationRequest.builder()
                                        .likes(2)
                                        .content("This is my very first post!")
                                        .date("03/05/2024")
                                        .build())
                                .build(),
                        PostRequest.builder()
                                .info(PostInformationRequest.builder()
                                        .likes(321)
                                        .content("My second post!!!!!")
                                        .date("04/05/2024")
                                        .build())
                                .build()
                )).build();

        UserResponse user = jsonNodeConverter.convert(userRequest, UserResponse.class);

        System.out.println(user);
    }
}