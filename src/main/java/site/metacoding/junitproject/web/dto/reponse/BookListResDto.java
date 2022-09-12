package site.metacoding.junitproject.web.dto.reponse;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BookListResDto {
    List<BookRespDto> items;

    @Builder
    public BookListResDto(List<BookRespDto> items) {
        this.items = items;
    }
}
