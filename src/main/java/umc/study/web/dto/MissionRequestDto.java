package umc.study.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class MissionDto {
        Long storeId;
        String missionSpec;
        Integer reward;
        LocalDate deadline;
    }
}
