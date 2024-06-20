package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class AddResultDto {
        Long storeId;
        LocalDateTime createdAt;
    }
}
