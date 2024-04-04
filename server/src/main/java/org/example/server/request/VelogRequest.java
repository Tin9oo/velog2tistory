package org.example.server.request;

import lombok.Builder;
import lombok.Getter;

public class VelogRequest {
    @Getter
    @Builder
    public static class PostList {
        String operationName;
        PostListVariables variables;
        String query;
    }

    @Getter
    @Builder
    public static class PostListVariables {
        String username;
        int limit;
    }

    @Getter
    @Builder
    public static class Post {
        String operationName;
        PostVariables variables;
        String query;
    }

    @Getter
    @Builder
    public static class PostVariables {
        String username;
        String url_slug;
    }
}
