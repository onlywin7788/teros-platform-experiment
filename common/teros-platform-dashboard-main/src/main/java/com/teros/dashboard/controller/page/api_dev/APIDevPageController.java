package com.teros.dashboard.controller.page.api_dev;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class APIDevPageController {

    // PAGE : API
    @GetMapping("/api-development/api")
    public String apiIndex(Model model) {
        return "contents/api_dev/api/index";
    }

    @GetMapping("/api-development/api/create")
    public String developmentCreate(Model model) {
        model.addAttribute("update", 0);
        return "contents/api_dev/api/contents";
    }

    @GetMapping("/api-development/api/{id}")
    public String developmentUpdate(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("update", 1);

        return "contents/api_dev/api/contents";
    }

    @GetMapping("/api-development/api/module/input_method")
    public String developmentAPIModuleInputMethod(Model model) {
        return "contents/api_dev/api/module/input_method";
    }


    // PAGE : API_PLAN
    @GetMapping("/api-development/api-plan")
    public String apiPlan(Model model) {
        return "contents/api_dev/api_plan/index";
    }

    // PAGE : API_DEPLOY
    @GetMapping("/api-development/api-deploy")
    public String apiDeploy(Model model) {
        return "contents/api_dev/api_deploy/index";
    }
}
