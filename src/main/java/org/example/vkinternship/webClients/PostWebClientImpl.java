package org.example.vkinternship.webClients;

import org.example.vkinternship.dto.Post;
import org.example.vkinternship.requests.AddPostRequest;
import org.example.vkinternship.requests.UpdatePostRequest;
import org.example.vkinternship.responses.AddPostResponse;
import org.example.vkinternship.responses.GetPostResponse;
import org.example.vkinternship.responses.UpdatePostResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class PostWebClientImpl implements PostWebClient {
    private final WebClient webClient;
    private final String link;

    public PostWebClientImpl(WebClient webClient, String link) {
        this.webClient = webClient;
        this.link = link;
    }

    @Override
    public GetPostResponse get(Long id) {
        return webClient
                .get()
                .uri(link + "/" + id.toString())
                .retrieve()
                .bodyToFlux(Post.class)
                .map(GetPostResponse::new)
                .blockFirst();
    }

    @Override
    public List<GetPostResponse> getAll() {
        return webClient
                .get()
                .uri(link)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class)
                .map(GetPostResponse::new)
                .collectList()
                .block();
    }

    @Override
    public AddPostResponse add(AddPostRequest addPostRequest) {
        return webClient
                .post()
                .uri(link)
                .bodyValue(addPostRequest)
                .retrieve()
                .bodyToFlux(Post.class)
                .map(AddPostResponse::new)
                .blockFirst();
    }

    @Override
    public UpdatePostResponse update(Long id, UpdatePostRequest updatePostRequest) {
        return webClient
                .put()
                .uri(link + "/" + id.toString())
                .bodyValue(updatePostRequest)
                .retrieve()
                .bodyToFlux(Post.class)
                .map(UpdatePostResponse::new)
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
