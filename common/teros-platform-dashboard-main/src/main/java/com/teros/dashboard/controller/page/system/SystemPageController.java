package com.teros.dashboard.controller.page.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemPageController {

    @GetMapping("/system/event-log")
    public String apiIndex(Model model) {
        return "contents/system/eventlog/index";
    }

    @GetMapping("/system/configuration")
    public String apiPlan(Model model) {
        return "contents/system/configuration/index";
    }

}
