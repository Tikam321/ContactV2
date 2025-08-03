package com.samsungsds.contact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class FavoriteController {

    @GetMapping("favorite/list")
    public void  FavoriteListResponse() {

    }

    @PostMapping("/favorite")
    public void addFavorite() {

    }

    @PostMapping("/favorite/delete")
    public void deleteFavorite() {

    }



}
