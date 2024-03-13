package org.example.vkinternship.service;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.requests.AddAlbumRequest;
import org.example.vkinternship.requests.UpdateAlbumRequest;
import org.example.vkinternship.responses.AddAlbumResponse;
import org.example.vkinternship.responses.GetAlbumResponse;
import org.example.vkinternship.responses.UpdateAlbumResponse;
import org.example.vkinternship.webClients.AlbumWebClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumWebClient albumWebClient;
    @Override
    @Cacheable(value="albums")
    public GetAlbumResponse get(Long id) {
        return albumWebClient.get(id);
    }

    @Override
    @Cacheable(value = "allAlbums", key="1")
    public List<GetAlbumResponse> getAll() {
        return albumWebClient.getAll();
    }

    @Override
    @CachePut(value="albums", key = "#addAlbumRequest.album().id()")
    @CacheEvict(value = "allAlbums", key = "1")
    public AddAlbumResponse add(AddAlbumRequest addAlbumRequest) {
        return albumWebClient.add(addAlbumRequest);
    }

    @Override
    @CachePut(value = "albums", key = "#id")
    @CacheEvict(value = "allAlbums", key = "1")
    public UpdateAlbumResponse update(Long id, UpdateAlbumRequest updateAlbumRequest) {
        return albumWebClient.update(id, updateAlbumRequest);
    }

    @Override
    @Caching( evict = {
            @CacheEvict("albums"),
            @CacheEvict(value = "allAlbums", key = "1")})
    public Void delete(Long id) {
        return albumWebClient.delete(id);
    }
}
