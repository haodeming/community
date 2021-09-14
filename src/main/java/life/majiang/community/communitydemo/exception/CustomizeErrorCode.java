package life.majiang.community.communitydemo.exception;



public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找到的问题不存在，换个试试");

    private String message;
    @Override
    public String getMessage(){
        return message;
    }
    CustomizeErrorCode(String message){
        this.message = message;
    }


}
