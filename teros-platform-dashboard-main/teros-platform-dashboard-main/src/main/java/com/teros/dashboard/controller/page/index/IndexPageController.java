package com.teros.dashboard.controller.page.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @GetMapping("/")
    public String hello(Model model){
        return "contents/index/index";
    }

}
