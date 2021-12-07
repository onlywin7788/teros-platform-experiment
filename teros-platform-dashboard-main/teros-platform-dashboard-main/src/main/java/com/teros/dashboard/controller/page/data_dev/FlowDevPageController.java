package com.teros.dashboard.controller.page.data_dev;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FlowDevPageController {

    //exec-group
    @GetMapping("/data-development/exec-group")
    public String executionGroup(Model model) {
        return "contents/data_dev/exec_group/exec_group";
    }

    //exec-group
    @GetMapping("/data-development/exec-group-flow-map")
    public String executionGroupFlowMap(Model model) {
        return "contents/data_dev/exec_group/exec_group_flow_map";
    }

    // flow-dev
    @GetMapping("/data-development/flow-dev")
    public String flowDev(Model model) {
        return "contents/data_dev/flow_dev/index";
    }

    @GetMapping("/data-development/flow-design/create")
    public String flowDDesignCreate(Model model) {
        model.addAttribute("update", 0);
        return "contents/data_dev/flow_dev/design/index";
    }

    @GetMapping("/data-development/flow-design/{id}")
    public String flowDesignUpdate(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("update", 1);
        return "contents/data_dev/flow_dev/design/index";
    }

    // connector
    @GetMapping("/data-development/connector")
    public String flowConnector(Model model) {
        return "contents/data_dev/connector/index";
    }

    // data-set
    @GetMapping("/data-development/data-set")
    public String flowDataSet(Model model) {
        return "contents/data_dev/data_set/index";
    }
}
