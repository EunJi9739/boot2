package org.koreait.model.board;

import lombok.RequiredArgsConstructor;
import org.koreait.controller.board.BoardForm;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardSaveService {

    private final BoardSaveValidator saveValidator;
    private final BoardDao boardDao;
    public void save(BoardForm boardForm){
        saveValidator.check(boardForm);
        boardDao.insert(boardForm);
    }
}
