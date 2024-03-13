package org.example.vkinternship.service;

import org.example.vkinternship.requests.AddPostRequest;
import org.example.vkinternship.requests.UpdatePostRequest;
import org.example.vkinternship.responses.*;

import java.util.List;

public interface PostService {
    GetPostResponse get(Long id);
    List<GetPostResponse> getAll();
    AddPostResponse add(AddPostRequest addPostRequest);
    UpdatePostResponse update(Long id, UpdatePostRequest updatePostRequest);
    Void delete(Long id);
}
