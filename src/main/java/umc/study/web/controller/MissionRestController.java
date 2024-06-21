package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.base.ApiResponse;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.web.dto.MemberMissionResponseDto;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
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

    @PostMapping("/finish/{memberMissionId}")
    @Operation(summary = "미션 완료 변경 API", description = "진행 중인 미션을 진행 완료로 바꾸는 API 입니다. query String 으로 memberId를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberMissionId", description = "멤버가 수행 중인 미션의 아이디 입니다!")
    })
    public ApiResponse<MemberMissionResponseDto.ChallengeResultDto> finishMission(@PathVariable(name = "memberMissionId") Long memberMissionId,
                                                                                  @RequestParam(name = "memberId") Long memberId) {
        MemberMission memberMission = memberMissionService.finishMemberMission(memberMissionId, memberId);

        return ApiResponse.onSuccess(MemberMissionConverter.toAddResultDto(memberMission));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDto.MissionPreViewListDto> getMissionList(@PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {


        Page<Mission> missionPage = missionQueryService.getMissionList(storeId, page);

        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDto(missionPage));
    }

    @GetMapping("/{memberId}/challenge")
    @Operation(summary = "유저의 진행 중인 미션 목록 조회 API", description = "유저가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDto.MissionPreViewListDto> getChallengeList(@PathVariable(name = "memberId") Long memberId, @RequestParam(name = "page") Integer page) {


        Page<Mission> missionPage = missionQueryService.getChallengeList(memberId, page);

        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDto(missionPage));
    }
}
