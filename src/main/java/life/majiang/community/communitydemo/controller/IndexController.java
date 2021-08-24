package life.majiang.community.communitydemo.controller;

import life.majiang.community.communitydemo.dto.QuestionDTO;
import life.majiang.community.communitydemo.mapper.QuestionMapper;
import life.majiang.community.communitydemo.mapper.UserMapper;
import life.majiang.community.communitydemo.model.Question;
import life.majiang.community.communitydemo.model.User;
import life.majiang.community.communitydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionsList = questionService.list();
        model.addAttribute("questions", questionsList);

        return "index";
    }


}
