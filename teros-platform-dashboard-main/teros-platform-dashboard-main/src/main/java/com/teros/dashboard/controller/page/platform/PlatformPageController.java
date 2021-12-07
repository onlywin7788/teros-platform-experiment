package com.teros.dashboard.controller.page.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlatformPageController {

    @GetMapping("/platform/management/overview")
    public String overview(Model model) {
        return "contents/platform/overview";
    }

    @GetMapping("/platform/management/application/api")
    public String apiMgmt(Model model) { return "contents/platform/application/api_mgmt"; }

    @GetMapping("/platform/management/application/data")
    public String dataMgmt(Model model) {
        return "contents/platform/application/data_mgmt";
    }
}
