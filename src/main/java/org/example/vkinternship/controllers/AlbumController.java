package org.example.vkinternship.controllers;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.requests.AddAlbumRequest;
import org.example.vkinternship.requests.UpdateAlbumRequest;
import org.example.vkinternship.responses.AddAlbumResponse;
import org.example.vkinternship.responses.GetAlbumResponse;
import org.example.vkinternship.responses.UpdateAlbumResponse;
import org.example.vkinternship.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/albums")
public class AlbumController {
    @Autowired
    private final AlbumService albumService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('albums:read')")
    public GetAlbumResponse getAlbum(@PathVariable Long id) {
        return albumService.get(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('albums:read')")
    public List<GetAlbumResponse> getAllAlbums() {
        return albumService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('albums:create')")
    public AddAlbumResponse addAlbum(AddAlbumRequest addAlbumRequest) {
        return albumService.add(addAlbumRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('albums:update')")
    public UpdateAlbumResponse updateAlbum(@PathVariable Long id, UpdateAlbumRequest updateAlbumRequest) {
        return albumService.update(id, updateAlbumRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('albums:delete')")
    public Void deleteAlbum(@PathVariable Long id) {
        return albumService.delete(id);
    }
}
