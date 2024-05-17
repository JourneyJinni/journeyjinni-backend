package com.ssafy.mvc.model.Board;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardListDto {

    private List<BoardDto> articles;
    private int currentPage;
    private int totalPageCount;
}
