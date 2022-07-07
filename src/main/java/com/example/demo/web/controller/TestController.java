package com.example.demo.web.controller;

import com.example.demo.config.JwtProvider;
import com.example.demo.web.dto.GetTockenResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/getToken")
    public GetTockenResponce getToken(@RequestParam (value= "Login") String login) {
        var responceDto = new GetTockenResponce(jwtProvider.generateAuthToken(login));
        return responceDto;
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody String token) {
        return jwtProvider.validateToken(token);
    }
}
