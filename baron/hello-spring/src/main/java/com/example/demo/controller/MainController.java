package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public ResponseEntity<?> getLatestEntry() {
        Entry data = new Entry("aaaa title", "bbbb content");
        return ResponseEntity.ok(data);
    }

    record Entry(String title, String content){}
}