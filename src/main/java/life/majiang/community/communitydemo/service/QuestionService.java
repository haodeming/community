package life.majiang.community.communitydemo.service;

import life.majiang.community.communitydemo.dto.QuestionDTO;
import life.majiang.community.communitydemo.mapper.QuestionMapper;
import life.majiang.community.communitydemo.mapper.UserMapper;
import life.majiang.community.communitydemo.model.Question;
import life.majiang.community.communitydemo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    //问题表级联用户表
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question:questions){
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
