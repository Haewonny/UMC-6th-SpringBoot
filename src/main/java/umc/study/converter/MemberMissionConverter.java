package umc.study.converter;

import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionResponseDto;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberMissionResponseDto.ChallengeResultDto toAddResultDto(MemberMission memberMission) {
        return MemberMissionResponseDto.ChallengeResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
