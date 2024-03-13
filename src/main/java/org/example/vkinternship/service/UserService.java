package org.example.vkinternship.service;

import org.example.vkinternship.requests.AddUserRequest;
import org.example.vkinternship.requests.UpdateUserRequest;
import org.example.vkinternship.responses.*;

import java.util.List;

public interface UserService {
    GetUserResponse get(Long id);
    List<GetUserResponse> getAll();
    AddUserResponse add(AddUserRequest addUserRequest);
    UpdateUserResponse update(Long id, UpdateUserRequest updateUserRequest);
    Void delete(Long id);
}
