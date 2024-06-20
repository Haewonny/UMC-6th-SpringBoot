package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDto;

public interface StoreCommandService {

    Store addStore(StoreRequestDto.AddDto request);
}
