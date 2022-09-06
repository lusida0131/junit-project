package site.metacoding.junitproject.web.dto.reponse;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CNRespDto<T> {
    private Integer code; // 1은 성공, -1 은 실패
    private String msg; // 에러메세지, 성공에 대한 메세지
    private T body;

    @Builder
    public CNRespDto(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
}
