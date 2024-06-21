package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDto.MissionPreViewDto missionPreViewDto(Mission mission) {
        return MissionResponseDto.MissionPreViewDto.builder()
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponseDto.MissionPreViewListDto missionPreViewListDto(Page<Mission> missionList) {
        List<MissionResponseDto.MissionPreViewDto> misssionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDto).collect(Collectors.toList());

        return MissionResponseDto.MissionPreViewListDto.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(misssionPreViewDTOList.size())
                .missionList(misssionPreViewDTOList)
                .build();
    }
}
