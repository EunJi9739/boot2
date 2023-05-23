package org.koreait.model.board;

public class BoardValidationException extends RuntimeException{
    public BoardValidationException(String msg){
        super(msg);
    }
}
