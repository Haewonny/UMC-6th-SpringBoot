package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDto;
import umc.study.web.dto.ReviewResponseDto;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDto.AddResultDto toAddResultDto(Review review) {
        return ReviewResponseDto.AddResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDto.ReviewDto request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .build();

    }
}
