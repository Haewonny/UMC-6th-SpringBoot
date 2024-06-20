package umc.study.web.dto;

import lombok.Getter;

public class ReviewRequestDto {
    @Getter
    public static class ReviewDto {
        Long storeId;
        String title;
        Float score;
    }
}
