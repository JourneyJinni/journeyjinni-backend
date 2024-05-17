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
public class BoardDto {

    private int articleNo;
    private String userId;
    private String userName;

    private String subject;
    private String content;
    private int hit;
    private String registerTime;
    private List<FileInfoDto> fileInfos;

}
