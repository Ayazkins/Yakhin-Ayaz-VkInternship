package org.example.vkinternship.requests;

import org.example.vkinternship.dto.user.Address;
import org.example.vkinternship.dto.user.Company;
import org.example.vkinternship.dto.user.User;

public record UpdateUserRequest(String name, String email, Address address, String phone, String website, Company company) {
}
