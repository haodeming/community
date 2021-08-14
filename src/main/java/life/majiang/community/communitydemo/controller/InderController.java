package life.majiang.community.communitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InderController {

    @GetMapping("/")
    public String hello(){
        return "index";
    }


}
