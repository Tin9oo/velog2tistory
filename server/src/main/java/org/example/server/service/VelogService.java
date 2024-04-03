package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.server.model.VelogListInfo;
import org.example.server.request.VelogRequest;
import org.example.server.util.VelogListResponseParser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Service
@Slf4j
public class VelogService {
    private final String VELOG_API_URL = "https://v2.velog.io/graphql";
    // query에 '\\n'이 들어있으면 에러가 발생함
    private final String QUERY_POSTLIST = "query Posts($cursor: ID, $username: String, $temp_only: Boolean, $tag: String, $limit: Int) {  posts(cursor: $cursor, username: $username, temp_only: $temp_only, tag: $tag, limit: $limit) {    id    title    short_description    thumbnail    user {      id      username      profile {        id        thumbnail        __typename      }      __typename    }    url_slug    released_at    updated_at    comments_count    tags    is_private    likes    __typename  }}";
    private final String QUERY_POST = "query ReadPost($username: String, $url_slug: String) {  post(username: $username, url_slug: $url_slug) {    id    title    released_at    updated_at    tags    body    short_description    is_markdown    is_private    is_temp    thumbnail    comments_count    url_slug    likes    liked    user {      id      username      profile {        id        display_name        thumbnail        short_bio        profile_links        __typename      }      velog_config {        title        __typename      }      __typename    }    comments {      id      user {        id        username        profile {          id          thumbnail          __typename        }        __typename      }      text      replies_count      level      created_at      level      deleted      __typename    }    series {      id      name      url_slug      series_posts {        id        post {          id          title          url_slug          user {            id            username            __typename          }          __typename        }        __typename      }      __typename    }    linked_posts {      previous {        id        title        url_slug        user {          id          username          __typename        }        __typename      }      next {        id        title        url_slug        user {          id          username          __typename        }        __typename      }      __typename    }    __typename  }}";

    public ArrayList<VelogListInfo> getVelogList(String username) {
        WebClient webClient = WebClient.builder().build();

        // Request Body 생성
        VelogRequest.PostList postList = VelogRequest.PostList.builder()
                .operationName("Posts")
                .variables(
                        VelogRequest.PostListVariables.builder()
                                .username(username)
                                .limit(100)
                                .build()
                )
                .query(QUERY_POSTLIST)
                .build();

        String response = webClient.post()
                .uri(VELOG_API_URL)
                .bodyValue(postList)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return VelogListResponseParser.parseVelogListResponse(response);
    }
}
