package org.example.vkinternship.webClients;

import org.example.vkinternship.requests.AddUserRequest;
import org.example.vkinternship.requests.UpdateUserRequest;
import org.example.vkinternship.responses.AddUserResponse;
import org.example.vkinternship.responses.GetUserResponse;
import org.example.vkinternship.responses.UpdateUserResponse;

import java.util.List;

public interface UserWebClient {
    GetUserResponse get(Long id);
    List<GetUserResponse> getAll();
    AddUserResponse add(AddUserRequest addUserRequest);
    UpdateUserResponse update(Long id, UpdateUserRequest updateUserRequest);
    Void delete(Long id);
}
