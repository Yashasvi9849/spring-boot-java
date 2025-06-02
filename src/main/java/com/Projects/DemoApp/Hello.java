package com.Projects.DemoApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/helloworld")
    public Response greet(@RequestParam String id) {
        return new Response("Hello world yashu", id);
    }


    static class Response {
        private String message;
        private String id;

        public Response(String message, String id) {
            this.message = message;
            this.id = id;
        }


        public String getMessage() {
            return message;
        }

        public String getId() {
            return id;
        }
    }
}


