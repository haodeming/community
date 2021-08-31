package life.majiang.community.communitydemo.controller;

import life.majiang.community.communitydemo.dto.PaginationDTO;
import life.majiang.community.communitydemo.mapper.UserMapper;
import life.majiang.community.communitydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }


}
