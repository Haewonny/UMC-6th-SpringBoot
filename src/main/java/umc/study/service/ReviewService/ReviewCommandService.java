package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDto;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDto.ReviewDto request);
}
