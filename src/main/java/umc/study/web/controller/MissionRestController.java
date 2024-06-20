package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.base.ApiResponse;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MemberMissionResponseDto;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MemberMissionService memberMissionService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDto.AddResultDto> addMission(@RequestBody @Valid MissionRequestDto.MissionDto request) {
        Mission mission = missionCommandService.addMission(request);

        return ApiResponse.onSuccess(MissionConverter.toAddResultDto(mission));
    }

    @PostMapping("/challenge/{missionId}")
    public ApiResponse<MemberMissionResponseDto.ChallengeResultDto> challengeMission(@PathVariable(name = "missionId") Long missionId,
                                                                                     @RequestParam(name = "memberId") Long memberId) {
        MemberMission memberMission = memberMissionService.addMemberMission(missionId, memberId);

        return ApiResponse.onSuccess(MemberMissionConverter.toAddResultDto(memberMission));
    }
}
