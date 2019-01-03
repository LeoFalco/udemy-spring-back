package com.github.leofalco.config;

//@Controller //note - this is a spring-boot controller, not @RestController
public class RewriteSwaggerURI {
    //    @RequestMapping("/doc")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}