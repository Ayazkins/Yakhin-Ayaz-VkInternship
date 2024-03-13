package org.example.vkinternship.controllers;

import lombok.AllArgsConstructor;
import org.example.vkinternship.requests.AddPostRequest;
import org.example.vkinternship.requests.UpdatePostRequest;
import org.example.vkinternship.responses.AddPostResponse;
import org.example.vkinternship.responses.GetPostResponse;
import org.example.vkinternship.responses.UpdatePostResponse;
import org.example.vkinternship.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private final PostService postService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:read')")
    public GetPostResponse getPost(@PathVariable Long id) {
        return postService.get(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('posts:read')")
    public List<GetPostResponse> getAllPosts() {
        return postService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('posts:create')")
    public AddPostResponse addPost(AddPostRequest addPostRequest) {
        return postService.add(addPostRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:update')")
    public UpdatePostResponse updatePost(@PathVariable Long id, UpdatePostRequest updatePostRequest) {
        return postService.update(id, updatePostRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('posts:delete')")
    public Void deletePost(@PathVariable Long id) {
        return postService.delete(id);
    }
}