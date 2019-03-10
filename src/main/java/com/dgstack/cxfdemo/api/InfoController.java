package com.dgstack.cxfdemo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class InfoController {

    @GetMapping("/api/info/user")
    public Principal principal(Principal principal){
        return principal;
    }
}
