package org.example.vkinternship.repository;

import org.example.vkinternship.models.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<MyUserDetails, Long> {
    Optional<MyUserDetails> findByName(String name);
}
