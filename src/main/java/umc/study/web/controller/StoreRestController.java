package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.base.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.StoreRequestDto;
import umc.study.web.dto.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDto.AddResultDto> join(@RequestBody @Valid StoreRequestDto.AddDto request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDto(store));
    }
}
