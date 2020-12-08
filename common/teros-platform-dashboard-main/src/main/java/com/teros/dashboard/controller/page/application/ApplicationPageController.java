package com.teros.dashboard.controller.page.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationPageController {

    @GetMapping("/application/overview")
    public String overview(Model model) {
        return "contents/application/overview/index";
    }

    @GetMapping("/application/api-management")
    public String apiMgmt(Model model) {
        return "contents/application/api_mgmt/index";
    }

    @GetMapping("/application/data-management")
    public String dataMgmt(Model model) {
        return "contents/application/data_mgmt/index";
    }

    @GetMapping("/application/services")
    public String services(Model model) {
        return "contents/application/services/index";
    }
}
