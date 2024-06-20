package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;

    @Override
    public Store addStore(StoreRequestDto.AddDto request) {
        Store newStore = StoreConverter.toStore(request);

        return storeRepository.save(newStore);
    }
}
