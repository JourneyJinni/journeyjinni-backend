package com.ssafy.mvc.controller;

import java.nio.charset.Charset;
import java.util.Map;

import com.ssafy.mvc.model.Board.BoardDto;
import com.ssafy.mvc.model.Board.BoardListDto;
import com.ssafy.mvc.service.BoardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardRestController {

    private BoardService boardService;

    public BoardRestController(BoardService boardService) {
        super();
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<?> writeArticle(@RequestBody BoardDto boardDto) {
        log.info("writeArticle boardDto - {}", boardDto);
        try {
            boardService.writeArticle(boardDto);
//			return ResponseEntity.ok().build();
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listArticle(@RequestParam Map<String, String> map) {
        log.info("listArticle map - {}", map);
        try {
            BoardListDto boardListDto = boardService.listArticle(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{articleno}")
    public ResponseEntity<BoardDto> getArticle(@PathVariable("articleno")  int articleNo) throws Exception {
        log.info("getArticle - 호출 : " + articleNo);
        boardService.updateHit(articleNo);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleNo), HttpStatus.OK);
    }

    @GetMapping("/modify/{articleno}")
    public ResponseEntity<BoardDto> getModifyArticle(@PathVariable("articleno")int articleno) throws Exception {
        log.info("getModifyArticle - 호출 : " + articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody BoardDto boardDto) throws Exception {
        log.info("modifyArticle - 호출 {}", boardDto);

        boardService.modifyArticle(boardDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable("articleno") int articleno) throws Exception {
        log.info("deleteArticle - 호출");
        boardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody  Map<String, Object> map) throws Exception {
        log.info(map.get("user_id") + " vs " + map.get("author"));
        if (map.get("user_id").equals(map.get("author"))) {

            return ResponseEntity.ok().build(); // 200 OK 반환
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized 반환
    }


    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}