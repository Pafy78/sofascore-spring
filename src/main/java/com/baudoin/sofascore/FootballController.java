package com.baudoin.sofascore;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class FootballController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Test Ok";
    }
}
