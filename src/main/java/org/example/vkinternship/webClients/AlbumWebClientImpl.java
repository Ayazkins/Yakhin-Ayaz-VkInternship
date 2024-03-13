package org.example.vkinternship.webClients;

import org.example.vkinternship.dto.Album;
import org.example.vkinternship.requests.AddAlbumRequest;
import org.example.vkinternship.requests.UpdateAlbumRequest;
import org.example.vkinternship.responses.AddAlbumResponse;
import org.example.vkinternship.responses.GetAlbumResponse;
import org.example.vkinternship.responses.UpdateAlbumResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class AlbumWebClientImpl implements AlbumWebClient {
    private final WebClient webClient;
    private final String link;

    public AlbumWebClientImpl(WebClient webClient, String link) {
        this.webClient = webClient;
        this.link = link;
    }

    @Override
    public GetAlbumResponse get(Long id) {
        return webClient
                .get()
                .uri(link + "/" + id.toString())
                .retrieve()
                .bodyToFlux(Album.class)
                .map(GetAlbumResponse::new)
                .blockFirst();
    }

    @Override
    public List<GetAlbumResponse> getAll() {
        return webClient
                        .get()
                        .uri(link)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(Album.class)
                        .map(GetAlbumResponse::new)
                        .collectList()
                        .block();
    }

    @Override
    public AddAlbumResponse add(AddAlbumRequest addAlbumRequest) {
        return webClient
                .post()
                .uri(link)
                .bodyValue(addAlbumRequest)
                .retrieve()
                .bodyToFlux(Album.class)
                .map(AddAlbumResponse::new)
                .blockFirst();
    }

    @Override
    public UpdateAlbumResponse update(Long id, UpdateAlbumRequest updateAlbumRequest) {
        return webClient
                .put()
                .uri(link + "/" + id.toString())
                .bodyValue(updateAlbumRequest)
                .retrieve()
                .bodyToFlux(Album.class)
                .map(UpdateAlbumResponse::new)
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
