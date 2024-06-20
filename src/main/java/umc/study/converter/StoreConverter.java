package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDto;
import umc.study.web.dto.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDto.AddResultDto toAddResultDto(Store store) {
        return StoreResponseDto.AddResultDto.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();

    }

    public static Store toStore(StoreRequestDto.AddDto request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static StoreResponseDto.ReviewPreViewDto reviewPreViewDto(Review review) {
        return StoreResponseDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getTitle())
                .build();
    }

    public static StoreResponseDto.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList) {
        List<StoreResponseDto.ReviewPreViewDto> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDto).collect(Collectors.toList());

        return StoreResponseDto.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
