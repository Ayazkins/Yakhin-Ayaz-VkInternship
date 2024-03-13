package org.example.vkinternship.controllers;

import lombok.AllArgsConstructor;
import org.example.vkinternship.requests.AddUserRequest;
import org.example.vkinternship.requests.UpdateUserRequest;
import org.example.vkinternship.responses.AddUserResponse;
import org.example.vkinternship.responses.GetUserResponse;
import org.example.vkinternship.responses.UpdateUserResponse;
import org.example.vkinternship.service.UserService;
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
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public GetUserResponse getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public List<GetUserResponse> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('users:create')")
    @CachePut(value = "users", key="#addUserRequest.user().id()")
    public AddUserResponse addUser(AddUserRequest addUserRequest) {
        return userService.add(addUserRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public UpdateUserResponse updatePost(@PathVariable Long id, UpdateUserRequest updateUserRequest) {
        return userService.update(id, updateUserRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public Void deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
