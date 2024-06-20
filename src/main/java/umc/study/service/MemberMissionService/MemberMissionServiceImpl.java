package umc.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;

import static umc.study.domain.enums.MissionStatus.CHALLENGING;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission addMemberMission(Long missionId, Long memberId) {

        MemberMission newMemberMission = MemberMission.builder()
                .member(memberRepository.getById(memberId))
                .mission(missionRepository.getById(missionId))
                .status(CHALLENGING)
                .build();

        return memberMissionRepository.save(newMemberMission);
    }

    @Override
    public MemberMission finishMemberMission(Long memberMissionId, Long memberId) {
        MemberMission memberMission = memberMissionRepository.getById(memberMissionId);

        memberMission.changeStatus();

        return memberMissionRepository.save(memberMission);
    }

}
