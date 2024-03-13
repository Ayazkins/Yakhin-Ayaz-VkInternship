package org.example.vkinternship.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.vkinternship.models.Access.*;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(Set.of(
            USERS_READ,
            USERS_CREATE,
            USERS_UPDATE,
            USERS_DELETE,
            POSTS_CREATE,
            POSTS_UPDATE,
            POSTS_DELETE,
            POSTS_READ,
            ALBUMS_CREATE,
            ALBUMS_READ,
            ALBUMS_DELETE,
            ALBUMS_UPDATE
    )),
    USERS(Set.of(
            USERS_READ,
            USERS_CREATE,
            USERS_UPDATE,
            USERS_DELETE
    )),
    POSTS(Set.of(
            POSTS_CREATE,
            POSTS_UPDATE,
            POSTS_DELETE,
            POSTS_READ
    )),
    ALBUMS(Set.of(
            ALBUMS_CREATE,
            ALBUMS_READ,
            ALBUMS_DELETE,
            ALBUMS_UPDATE
    )),
    POSTS_VIEWER(Set.of(
            POSTS_READ
    )),
    ALBUM_VIEWER(Set.of(
            ALBUMS_READ
    )),
    USERS_VIEWER(Set.of(
            USERS_READ
    )),
    POSTS_EDITOR(Set.of(
            POSTS_READ,
            POSTS_UPDATE
    )),
    USERS_EDITOR(Set.of(
            USERS_READ,
            USERS_UPDATE
    )),
    ALBUMS_EDITOR(Set.of(
            ALBUMS_READ,
            ALBUMS_UPDATE
    )),
    MODERATOR(Set.of(
            USERS_READ,
            POSTS_READ,
            ALBUMS_READ,
            ALBUMS_DELETE,
            POSTS_DELETE,
            USERS_DELETE
    ));

    private final Set<Access> accessList;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getAccessList()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getAccess()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
    }
