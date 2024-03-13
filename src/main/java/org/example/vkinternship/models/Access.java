package org.example.vkinternship.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Access {
    USERS_READ("users:read"),
    USERS_UPDATE("users:update"),
    USERS_DELETE("users:delete"),
    USERS_CREATE("users:create"),
    POSTS_READ("posts:read"),
    POSTS_DELETE("posts:delete"),
    POSTS_UPDATE("posts:update"),
    POSTS_CREATE("posts:create"),
    ALBUMS_READ("albums:read"),
    ALBUMS_CREATE("albums:create"),
    ALBUMS_UPDATE("albums:update"),
    ALBUMS_DELETE("albums:delete");
    private final String access;
}
