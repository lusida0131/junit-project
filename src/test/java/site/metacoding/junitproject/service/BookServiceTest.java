package site.metacoding.junitproject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.metacoding.junitproject.domain.BookRepository;
import site.metacoding.junitproject.util.MailSender;
import site.metacoding.junitproject.web.dto.BookRespDto;
import site.metacoding.junitproject.web.dto.BookSaveReqDto;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 가짜 메모리환경 만들기
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MailSender mailSender;

    @Test
    void 책등록하기() {
        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("lusida");
        dto.setAuthor("박현성");

        //stub(가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);
        //when
        BookRespDto bookRespDto = bookService.책등록하기(dto);
        //then
        //Assertions.assertEquals(dto.getTitle(), bookRespDto.getTitle());  비추
        //Assertions.assertEquals(dto.getAuthor(), bookRespDto.getAuthor()); 비추

        assertThat(dto.getTitle()).isEqualTo(bookRespDto.getTitle());
        assertThat(dto.getAuthor()).isEqualTo(bookRespDto.getAuthor());
    }

    @Test
    void 책목록보기() {
    }

    @Test
    void 책한건보기() {
    }

    @Test
    void 책삭제하기() {
    }

    @Test
    void 책수정하기() {
    }
}