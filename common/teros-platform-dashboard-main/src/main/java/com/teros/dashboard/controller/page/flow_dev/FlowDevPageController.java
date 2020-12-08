package com.teros.dashboard.controller.page.flow_dev;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlowDevPageController {

    @GetMapping("/flow-development/flow-design")
    public String flowDesign(Model model) {
        return "contents/flow_dev/flow_design/index";
    }

    @GetMapping("/flow-development/connector")
    public String flowConnector(Model model) {
        return "contents/flow_dev/connector/index";
    }

    @GetMapping("/flow-development/data-set")
    public String flowDataSet(Model model) {
        return "contents/flow_dev/data_set/index";
    }
}
