package edu.lab.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloPageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "redirect:" + CityController.CONTROLLER_BASE_URL;
    }

}
