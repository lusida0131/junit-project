package site.metacoding.junitproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.junitproject.domain.Book;
import site.metacoding.junitproject.domain.BookRepository;
import site.metacoding.junitproject.web.dto.BookRespDto;
import site.metacoding.junitproject.web.dto.BookSaveReqDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    // 1. 책 등록하기
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책등록하기(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        return new BookRespDto().toDto(bookPS);
    }
    // 2. 책 목록보기
    public List<BookRespDto> 책목록보기() {
        return bookRepository.findAll().stream()
                .map(new BookRespDto() :: toDto)
                .collect(Collectors.toList());
    }

    // 3. 책 한건 보기
    public BookRespDto 책한건보기(BookRespDto dto) {
        Book bookPS = bookRepository.findById(dto.getId()).get();
        return new BookRespDto().toDto(bookPS);
    }
    // 4. 책 삭제
    public BookRespDto 책삭제하기(BookRespDto dto) {
        Book bookPS = bookRepository.deleteById(dto.getId());
        return new BookRespDto().toDto(bookPS);
    }
    // 5. 책 수정

}
