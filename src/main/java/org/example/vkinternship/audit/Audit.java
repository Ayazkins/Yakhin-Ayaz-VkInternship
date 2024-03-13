package org.example.vkinternship.audit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vkinternship.models.Role;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Audit {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @LastModifiedDate
    private LocalDateTime timestamp;

    private String url;

    private String method;
}