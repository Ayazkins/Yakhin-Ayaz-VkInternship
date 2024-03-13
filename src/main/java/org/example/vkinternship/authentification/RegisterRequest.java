package org.example.vkinternship.authentification;

import org.example.vkinternship.models.MyUserDetails;
import org.example.vkinternship.models.Role;

public record RegisterRequest(String name, String password, Role role) {
}
