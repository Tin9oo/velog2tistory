package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.VelogInfo;
import org.example.server.service.VelogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/velog-list")
@RequiredArgsConstructor
public class VelogController {
    private final VelogService velogService;

    @GetMapping("{username}")
    public void velogList(@PathVariable("username") String username) {
        ArrayList<VelogInfo.List> velogList =  velogService.getVelogList(username);
        velogService.getVelogPost(velogList, username);
    }
}
