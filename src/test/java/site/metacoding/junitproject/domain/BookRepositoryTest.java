package site.metacoding.junitproject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void 데이터준비() {
        String title = "junit";
        String author = "겟인데어";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    // 1. 책 등록
    @Test
    void 책등록() {
        //given
        String title = "junit5";
        String author = "lusida";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        //when
        Book bookPS = bookRepository.save(book);

        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }
    // 2. 책 목록 보기
    @Test
    void 책목록보기() {
        //given
        String title = "junit";
        String author = "겟인데어";

        //when
        List<Book> books = bookRepository.findAll();

        //then
        assertEquals(title, books.get(0).getTitle());
        assertEquals(author, books.get(0).getAuthor());

    }
    // 3. 책 한 건 보기
    @Sql("classpath:db/tableInit.sql")
    @Test
    void 책한건보기() {
        //given
        String title = "junit";
        String author = "겟인데어";

        //when
        Book bookPS = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }
    // 4. 책 수정
    @Sql("classpath:db/tableInit.sql")
    @Test
    void 삭제() {
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(id);
        //then
        assertFalse(bookRepository.findById(id).isPresent());
    }
    // 5. 책 삭제
}