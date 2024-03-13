package org.example.vkinternship.service;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.requests.AddUserRequest;
import org.example.vkinternship.requests.UpdateUserRequest;
import org.example.vkinternship.responses.AddUserResponse;
import org.example.vkinternship.responses.GetUserResponse;
import org.example.vkinternship.responses.UpdateUserResponse;
import org.example.vkinternship.webClients.UserWebClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserWebClient userWebClient;
    @Override
    @Cacheable(value = "users", unless = "#result == null")
    public GetUserResponse get(Long id) {
        return userWebClient.get(id);
    }

    @Override
    @Cacheable(value = "allUsers", key="1")
    public List<GetUserResponse> getAll() {
        return userWebClient.getAll();
    }

    @Override
    @CacheEvict(value = "allUsers", key="1")
    public AddUserResponse add(AddUserRequest addUserRequest) {
        return userWebClient.add(addUserRequest);
    }

    @Override
    @CachePut(value = "users", key = "#id")
    @CacheEvict(value = "allUsers")
    public UpdateUserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        return userWebClient.update(id, updateUserRequest);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "users"), @CacheEvict(value = "allUsers", key="1")})
    public Void delete(Long id) {
        return userWebClient.delete(id);
    }
}