package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

import java.time.LocalDateTime;

public class MissionConverter {
    public static MissionResponseDto.AddResultDto toAddResultDto(Mission mission) {
        return MissionResponseDto.AddResultDto.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDto.MissionDto request) {
        return Mission.builder()
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }
}
