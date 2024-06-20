package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDto;

public interface MissionCommandService {
    Mission addMission(MissionRequestDto.MissionDto request);
}
