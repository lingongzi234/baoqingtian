package com.tjpu22t01.dataanalyze.controller;


import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("logic/gmtest")
@RestController
@Log
public class Test {
    @RequestMapping("/hello")
public String checkCurrentUser() {
    return "user";
}
}
