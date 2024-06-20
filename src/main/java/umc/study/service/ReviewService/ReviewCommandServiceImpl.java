package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.ReviewRequestDto;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review addReview(ReviewRequestDto.ReviewDto request) {
        Review newReview = ReviewConverter.toReview(request);

        newReview.setStore(storeRepository.findById(request.getStoreId()).get());
        return reviewRepository.save(newReview);
    }
}
