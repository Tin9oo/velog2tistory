package org.example.server.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VelogListInfo {
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
