package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.MissionRequestDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission addMission(MissionRequestDto.MissionDto request) {
        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(storeRepository.findById(request.getStoreId()).get());

        return missionRepository.save(newMission);
    }
}
