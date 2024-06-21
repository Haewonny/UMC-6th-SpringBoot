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
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.web.dto.ReviewRequestDto;
import umc.study.web.dto.ReviewResponseDto;
import umc.study.web.dto.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDto.AddResultDto> addReview(@RequestBody @Valid ReviewRequestDto.ReviewDto request) {
        Review review = reviewCommandService.addReview(request);

        return ApiResponse.onSuccess(ReviewConverter.toAddResultDto(review));

    }

    @GetMapping("/{memberId}")
    @Operation(summary = "유저가 작성한 리뷰 목록 조회 API", description = "특정 유저가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDto.ReviewPreViewListDto> getReviewList(@PathVariable(name = "memberId") Long memberId, @RequestParam(name = "page") Integer page) {

        Page<Review> reviewPage = reviewQueryService.getReviewList(memberId, page);

        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDto(reviewPage));
    }
}
