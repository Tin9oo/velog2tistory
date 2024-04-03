package org.example.server.util;

import lombok.extern.slf4j.Slf4j;
import org.example.server.model.VelogListInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Slf4j
public class VelogListResponseParser {
    public static ArrayList<VelogListInfo> parseVelogListResponse(String jsonString) {
        JSONObject jObject = new JSONObject(jsonString);
        JSONObject jObjectData = jObject.getJSONObject("data");
        JSONArray jObjectPosts = jObjectData.getJSONArray("posts");

        ArrayList<VelogListInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < jObjectPosts.length(); i++) {
            JSONObject postInfo = (JSONObject) jObjectPosts.get(i);

            VelogListInfo velogListInfo = VelogListInfo.builder()
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
}
