package org.example.vkinternship.service;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.requests.AddPostRequest;
import org.example.vkinternship.requests.UpdatePostRequest;
import org.example.vkinternship.responses.AddPostResponse;
import org.example.vkinternship.responses.GetPostResponse;
import org.example.vkinternship.responses.UpdatePostResponse;
import org.example.vkinternship.webClients.PostWebClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostWebClient postWebClient;
    @Override
    @CachePut(value="posts")
    public GetPostResponse get(Long id) {
        return postWebClient.get(id);
    }

    @Override
    @Cacheable(value = "allPosts", key="1")
    public List<GetPostResponse> getAll() {
        return postWebClient.getAll();
    }

    @Override
    @CachePut(value = "posts", key = "#addPostRequest.post().id()")
    @CacheEvict(value = "allPosts", key = "1")
    public AddPostResponse add(AddPostRequest addPostRequest) {
        return postWebClient.add(addPostRequest);
    }

    @Override
    @CachePut(value = "posts", key = "#id")
    @CacheEvict(value = "allPosts", key = "1")
    public UpdatePostResponse update(Long id, UpdatePostRequest updatePostRequest) {
        return postWebClient.update(id, updatePostRequest);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "posts"),
            @CacheEvict(value = "allPosts", key = "1")})
    public Void delete(Long id) {
        return postWebClient.delete(id);
    }
}