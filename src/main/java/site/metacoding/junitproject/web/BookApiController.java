package site.metacoding.junitproject.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.metacoding.junitproject.service.BookService;
import site.metacoding.junitproject.web.dto.reponse.BookRespDto;
import site.metacoding.junitproject.web.dto.reponse.CNRespDto;
import site.metacoding.junitproject.web.dto.request.BookSaveReqDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BookApiController {

    private final BookService bookService;

    // 1. 책 등록하기
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult) {
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);

        //AOP 처리하는게 좋음
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            log.info(errorMap.toString());
            return new ResponseEntity<>(CNRespDto.builder().code(-1).msg(errorMap.toString()).body(bookRespDto).build(), HttpStatus.BAD_REQUEST); // 400 잘못된 요청
        }
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 저장 성공").body(bookRespDto).build(), HttpStatus.CREATED); // 201 insert bookRespDto json
    }
    // 2. 책 목록보기
    @GetMapping("/api/v1/booklist")
    public ResponseEntity<?> getBookList(@RequestBody BookRespDto bookRespDto) {
        List<BookRespDto> list = bookService.책목록보기();
        return new ResponseEntity<>(list, HttpStatus.OK);
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
