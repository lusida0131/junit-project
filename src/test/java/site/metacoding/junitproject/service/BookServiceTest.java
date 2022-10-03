package site.metacoding.junitproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import site.metacoding.junitproject.domain.Book;
import site.metacoding.junitproject.domain.BookRepository;
import site.metacoding.junitproject.util.MailSender;
import site.metacoding.junitproject.web.dto.reponse.BookListResDto;
import site.metacoding.junitproject.web.dto.reponse.BookRespDto;
import site.metacoding.junitproject.web.dto.request.BookSaveReqDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
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

        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    void 책목록보기() {
        //given

        //stub
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Junit 강의", "매타코딩"));
        books.add(new Book(2L, "lusida", "박현성"));

        when(bookRepository.findAll()).thenReturn(books);
        //when
        BookListResDto bookRespDtoList = bookService.책목록보기();


        //then
        assertThat(bookRespDtoList.getItems().get(0).getTitle()).isEqualTo("Junit 강의");
        assertThat(bookRespDtoList.getItems().get(1).getTitle()).isEqualTo("lusida");
    }

    @Test
    void 책한건보기() {
        //given
        Long id = 1L;

        //stub
        Book book = new Book(1L, "Junit 강의", "매타코딩");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);
        //when
        BookRespDto bookRespDto = bookService.책한건보기(1L);
        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
    }
    @Test
    void 책수정하기() {
        //given
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("Junit lusida");
        dto.setAuthor("lusida");
        //stub
        Book book = new Book(1L, "Junit 강의", "매타코딩");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);
        //when
        BookRespDto bookRespDto = bookService.책수정하기(id, dto);
        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
}