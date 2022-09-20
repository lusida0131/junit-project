package site.metacoding.junitproject.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import site.metacoding.junitproject.service.BookService;
import site.metacoding.junitproject.web.dto.reponse.BookListResDto;
import site.metacoding.junitproject.web.dto.reponse.BookRespDto;
import site.metacoding.junitproject.web.dto.reponse.CNRespDto;
import site.metacoding.junitproject.web.dto.request.BookSaveReqDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BookApiController {

    private final BookService bookService;

    // 1. 책 등록하기
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult) {

        //AOP 처리하는게 좋음
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            log.info(errorMap.toString());
            throw new RuntimeException(errorMap.toString());
        }
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);

        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 저장 성공").body(bookRespDto).build(), HttpStatus.CREATED); // 201 insert bookRespDto json
    }
    @PostMapping("/api/v2/book")
    public ResponseEntity<?> saveBookV2(@RequestBody BookSaveReqDto bookSaveReqDto) {
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 저장 성공").body(bookRespDto).build(), HttpStatus.CREATED); // 201 insert bookRespDto json
    }
    // 2. 책 목록보기
    @GetMapping("/api/v1/booklist")
    public ResponseEntity<?> getBookList(@RequestBody BookRespDto bookRespDto) {
        BookListResDto list = bookService.책목록보기();
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 목록 가져오기 성공").body(list).build(), HttpStatus.OK);
    }

    // 3. 책 한건 보기
    @GetMapping("/api/v1/book/{id}")
    public ResponseEntity<?> getBookOne(@PathVariable Long id) {
        BookRespDto bookRespDto = bookService.책한건보기(id);
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 한건 가져오기 성공").body(bookRespDto).build(), HttpStatus.OK);
    }
    // 4. 책 삭제
    @DeleteMapping("/api/v1/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.책삭제하기(id);
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 삭제하기 성공").body(null).build(), HttpStatus.NO_CONTENT);
    }
    // 5. 책 수정
    @PutMapping("/api/v1/book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            log.info(errorMap.toString());
            throw new RuntimeException(errorMap.toString());
        }
        BookRespDto bookRespDto = bookService.책수정하기(id, bookSaveReqDto);
        return new ResponseEntity<>(CNRespDto.builder().code(1).msg("글 수정하기 성공").body(bookRespDto).build(), HttpStatus.OK);
    }

}
