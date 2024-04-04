package org.example.server.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

public class VelogInfo {

    @Getter
    @Builder
    public static class List {
        String title;
        String urlSlug;
        Object thumbnail;

        @Override
        public String toString() {
            return "\ntitle: " + title +
                    "\nurl_slug: " + urlSlug +
                    "\nthumbnail: " + thumbnail;
        }
    }

    @Getter
    @Builder
    public static class Post {
        String title;
        ArrayList<String> tags;
        String body;

        @Override
        public String toString() {
            return "\ntitle: " + title +
                    "\ntags: " + tags +
                    "\nbody: " + body;
        }
    }
}
