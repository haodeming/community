package life.majiang.community.communitydemo.controller;

import life.majiang.community.communitydemo.dto.QuestionDTO;
import life.majiang.community.communitydemo.mapper.QuestionMapper;
import life.majiang.community.communitydemo.model.Question;
import life.majiang.community.communitydemo.model.User;
import life.majiang.community.communitydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        if (question.getTitle() == null || question.getTitle().equals("") ){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (question.getDescription() == null || question.getDescription().equals("") ){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (question.getTag() == null || question.getTag().equals("") ){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";

    }
}
