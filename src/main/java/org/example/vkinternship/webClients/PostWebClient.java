package org.example.vkinternship.webClients;

import org.example.vkinternship.requests.AddPostRequest;
import org.example.vkinternship.requests.UpdatePostRequest;
import org.example.vkinternship.responses.AddPostResponse;
import org.example.vkinternship.responses.GetPostResponse;
import org.example.vkinternship.responses.UpdatePostResponse;

import java.util.List;

public interface PostWebClient {
    GetPostResponse get(Long id);
    List<GetPostResponse> getAll();
    AddPostResponse add(AddPostRequest addPostRequest);
    UpdatePostResponse update(Long id, UpdatePostRequest updatePostRequest);
    Void delete(Long id);
}
