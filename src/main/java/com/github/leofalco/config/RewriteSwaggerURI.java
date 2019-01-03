package com.github.leofalco.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //note - this is a spring-boot controller, not @RestController
public class RewriteSwaggerURI {
    @RequestMapping("/doc")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}