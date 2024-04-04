package org.example.server.util;

import lombok.extern.slf4j.Slf4j;
import org.example.server.model.VelogInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Slf4j
public class VelogParser {
    public static ArrayList<VelogInfo.List> parseList(String jsonString) {
        JSONObject jObject = new JSONObject(jsonString);
        JSONObject jObjectData = jObject.getJSONObject("data");
        JSONArray jObjectPosts = jObjectData.getJSONArray("posts");

        ArrayList<VelogInfo.List> arrayList = new ArrayList<>();
        for (int i = 0; i < jObjectPosts.length(); i++) {
            JSONObject postInfo = (JSONObject) jObjectPosts.get(i);

            VelogInfo.List velogListInfo = VelogInfo.List.builder()
                    .title(postInfo.getString("title"))
                    .thumbnail(postInfo.get("thumbnail"))
                    .urlSlug(postInfo.getString("url_slug"))
                    //TODO tags 포함
                    .build();

            arrayList.add(velogListInfo);
            log.info(velogListInfo.toString());
        }

        return arrayList;
    }

    public static VelogInfo.Post parsePost(String jsonString) {
        JSONObject jObject = new JSONObject(jsonString);
        JSONObject jObjectData = jObject.getJSONObject("data");
        JSONObject jObjectPost = jObjectData.getJSONObject("post");

        VelogInfo.Post velogPostInfo = VelogInfo.Post.builder()
                .title(jObjectPost.getString("title"))
                .tags(parseJArray2ArrayList(jObjectPost.getJSONArray("tags")))
                .body(jObjectPost.getString("body"))
                .build();

        log.info(velogPostInfo.toString());

        return velogPostInfo;
    }

    private static ArrayList<String> parseJArray2ArrayList(JSONArray jsonArray) {
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tags.add((String) jsonArray.get(i));
        }
        return tags;
    }
}
