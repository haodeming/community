package life.majiang.community.communitydemo.model;

import lombok.Data;
import lombok.Getter;

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
