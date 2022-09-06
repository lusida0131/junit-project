package site.metacoding.junitproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.metacoding.junitproject.service.BookService;
import site.metacoding.junitproject.web.dto.reponse.BookRespDto;
import site.metacoding.junitproject.web.dto.reponse.CNRespDto;
import site.metacoding.junitproject.web.dto.request.BookSaveReqDto;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    // 1. 책 등록하기
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody BookSaveReqDto bookSaveReqDto) {
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);
        CNRespDto<?> cnRespDto = CNRespDto.builder().code(1).msg("글 저장 성공").body(bookRespDto).build();
        return new ResponseEntity<>(cnRespDto, HttpStatus.CREATED); // 201 insert bookRespDto json
    }
    // 2. 책 목록보기
    public ResponseEntity<?> getBookList() {
        return null;
    }
    // 3. 책 한건 보기
    public ResponseEntity<?> getBookOne() {
        return null;
    }
    // 4. 책 삭제
    public ResponseEntity<?> deleteBook() {
        return null;
    }
    // 5. 책 수정
    public ResponseEntity<?> updateBook() {
        return null;
    }

}
