package site.metacoding.junitproject.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.junitproject.domain.Book;

@Getter
@Setter
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
