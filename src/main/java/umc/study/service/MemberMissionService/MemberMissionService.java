package umc.study.service.MemberMissionService;

import umc.study.domain.mapping.MemberMission;

public interface MemberMissionService {
    MemberMission addMemberMission(Long missionId, Long memberId);

    MemberMission finishMemberMission(Long memberMissionId, Long memberId);
}
