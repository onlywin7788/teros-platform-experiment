package com.teros.dashboard.controller.page.identity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdentityPageController {

    @GetMapping("/identity/groups")
    public String overview(Model model) {
        return "contents/identity/groups/index";
    }

    @GetMapping("/identity/users")
    public String apiMgmt(Model model) {
        return "contents/identity/users/index";
    }

}
