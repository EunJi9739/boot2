package org.koreait.model.board;


import org.koreait.controller.board.BoardForm;
import org.koreait.validators.RequiredValidator;
import org.koreait.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class BoardSaveValidator implements Validator<BoardForm> {

    @Override
    public void check(BoardForm boardForm) {
        checkRequired(boardForm.getContent(), new BoardValidationException("내용을 입력하세요."));
        checkRequired(boardForm.getSubject(), new BoardValidationException("제목을 입력하세요."));

    }
}
