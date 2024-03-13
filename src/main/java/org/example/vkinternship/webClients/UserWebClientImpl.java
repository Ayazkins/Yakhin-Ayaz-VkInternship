package org.example.vkinternship.webClients;

import org.example.vkinternship.dto.user.User;
import org.example.vkinternship.requests.AddUserRequest;
import org.example.vkinternship.requests.UpdateUserRequest;
import org.example.vkinternship.responses.AddUserResponse;
import org.example.vkinternship.responses.GetUserResponse;
import org.example.vkinternship.responses.UpdateUserResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class UserWebClientImpl implements UserWebClient {
    private final WebClient webClient;
    private final String link;

    public UserWebClientImpl(WebClient webClient, String link) {
        this.webClient = webClient;
        this.link = link;
    }

    @Override
    public GetUserResponse get(Long id) {
        return webClient
                .get()
                .uri(link + "/" + id.toString())
                .retrieve()
                .bodyToFlux(User.class)
                .map(GetUserResponse::new)
                .blockFirst();
    }

    @Override
    public List<GetUserResponse> getAll() {
        return webClient
                .get()
                .uri(link)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class)
                .map(GetUserResponse::new)
                .collectList()
                .block();
    }

    @Override
    public AddUserResponse add(AddUserRequest addUserRequest) {
        return webClient
                .post()
                .uri(link)
                .bodyValue(addUserRequest)
                .retrieve()
                .bodyToFlux(User.class)
                .map(AddUserResponse::new)
                .blockFirst();
    }

    @Override
    public UpdateUserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        return webClient
                .put()
                .uri(link + "/" + id.toString())
                .bodyValue(updateUserRequest)
                .retrieve()
                .bodyToFlux(User.class)
                .map(UpdateUserResponse::new)
                .blockFirst();
    }

    @Override
    public Void delete(Long id) {
        return webClient
                .delete()
                .uri(link + "/" + id.toString())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
