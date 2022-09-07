package site.metacoding.junitproject.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.junitproject.domain.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookSaveReqDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @Size(min = 2, max = 20)
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
