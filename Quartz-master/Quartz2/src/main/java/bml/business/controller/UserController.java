package bml.business.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {


    @GetMapping({"/index","/","login"})
    public String index() {
        return "index";
    }

    @GetMapping("admin/quartz")
    public String quartz() {
        return "quartz";
    }

}

