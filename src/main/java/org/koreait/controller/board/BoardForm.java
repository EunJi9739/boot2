package org.koreait.controller.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardForm {
    @NotBlank
    private String subject;
    @NotBlank
    private String Content;
    private Long id;
}
