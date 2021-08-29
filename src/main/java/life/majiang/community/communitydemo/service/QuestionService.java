package life.majiang.community.communitydemo.service;

import life.majiang.community.communitydemo.dto.PaginationDTO;
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
    public PaginationDTO list(Integer page,Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if(page<1){
            page = 1;
        }
        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }


        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question:questions){
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}