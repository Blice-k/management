package com.library.management.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @GetMapping("@{/templates/library.png")
    public Resource getImage() {
        return new ClassPathResource("@{/templates/library.png");
    }
}
