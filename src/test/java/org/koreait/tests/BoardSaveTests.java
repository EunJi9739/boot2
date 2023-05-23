package org.koreait.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controller.board.BoardForm;
import org.koreait.model.board.Board;
import org.koreait.model.board.BoardDao;
import org.koreait.model.board.BoardSaveService;
import org.koreait.model.board.BoardValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class BoardSaveTests {

    @Autowired
    private BoardSaveService saveService;

    @Autowired
    private BoardDao boardDao;

    private BoardForm getBoardForm(){
        BoardForm bf = new BoardForm();
        bf.setSubject("제목!");
        bf.setContent("내용!");

        return bf;
    }

    @Test
    @DisplayName("게시글 등록 테스트 - 성공 시 예외 X")
    void registerSaveSuccessTest(){
        assertDoesNotThrow(() -> {
            BoardForm bf = getBoardForm();
            saveService.save(bf);
        });
    }

    @Test
    @DisplayName("필수항목(content, subject) 입력 테스트 - 검증 실패 시 BoardValidationException 발생")
    void requiredFieldTest(){
        assertAll(
                //content - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm bf = getBoardForm();
                    bf.setContent(null);
                    saveService.save(bf);
                }),
                //content - 빈 값
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm bf = getBoardForm();
                    bf.setContent("   ");
                    saveService.save(bf);
                }),
                //subject - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm bf = getBoardForm();
                    bf.setContent(null);
                    saveService.save(bf);
                }),
                //subject - 빈 값
                () -> assertThrows(BootstrapMethodError.class, () -> {
                    BoardForm bf = getBoardForm();
                    bf.setContent("    ");
                    saveService.save(bf);
                })
        );
    }

    @Test
    @DisplayName("등록한 게시글이 일치하는지 확인")
    void registerDataTest(){
        BoardForm bf = getBoardForm();
        assertDoesNotThrow(() -> {
            boardDao.insert(bf);
        });

        Long id = bf.getId();
        if(id == null){
            //등록된 게시글 번호가 없으면 실패
            fail();
        }

        Board board = boardDao.get(id);
        if(board==null){
            //조회된 게시글이 없으면 실패
            fail();
        }

        assertEquals(bf.getContent(),board.getContent());
        assertEquals(bf.getSubject(),board.getSubject());
    }
}
