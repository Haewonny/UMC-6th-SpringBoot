package umc.study.converter;

import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDto;
import umc.study.web.dto.StoreResponseDto;

import java.time.LocalDateTime;

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
}
