package com.example.demo.config.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
@GetMapping("/test")
public String getTestPage(){
return "test";
}


}

