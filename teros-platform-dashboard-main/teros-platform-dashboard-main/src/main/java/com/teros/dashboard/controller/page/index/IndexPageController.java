package com.teros.dashboard.controller.page.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

//    @GetMapping("/")
//    public String hello(Model model){
//        return "contents/index/index";
//    }

    // PAGE : LOGIN
    @GetMapping("/")
    public String Login(Model model) {
        return "contents/api_dev/login/index";
    }

    // PAGE : MAIN
    @GetMapping("/main")
    public String Main(Model model) {
        return "contents/index/index";
    }

}
