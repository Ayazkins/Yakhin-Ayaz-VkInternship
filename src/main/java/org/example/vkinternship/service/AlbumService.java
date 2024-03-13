package org.example.vkinternship.service;

import org.example.vkinternship.requests.AddAlbumRequest;
import org.example.vkinternship.requests.UpdateAlbumRequest;
import org.example.vkinternship.responses.AddAlbumResponse;
import org.example.vkinternship.responses.GetAlbumResponse;
import org.example.vkinternship.responses.UpdateAlbumResponse;

import java.util.List;

public interface AlbumService {
    GetAlbumResponse get(Long id);
    List<GetAlbumResponse> getAll();
    AddAlbumResponse add(AddAlbumRequest addAlbumRequest);
    UpdateAlbumResponse update(Long id, UpdateAlbumRequest updateAlbumRequest);
    Void delete(Long id);
}
