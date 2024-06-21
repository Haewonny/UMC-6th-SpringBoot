package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;

import static umc.study.domain.enums.MissionStatus.CHALLENGING;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return missionPage;
    }

    public Page<Mission> getChallengeList(Long memberId, Integer page) {
        MemberMission memberMission = memberMissionRepository.findByIdAndStatus(memberId, CHALLENGING);
        Page<Mission> missionPage = missionRepository.findAllById(memberMission.getMission().getId(), PageRequest.of(page, 10));
        log.info(missionPage.toString());

        return missionPage;
    }
}
