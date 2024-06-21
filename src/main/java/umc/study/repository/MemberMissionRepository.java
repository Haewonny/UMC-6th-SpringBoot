package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    MemberMission findByIdAndStatus(Long memberId, MissionStatus missionStatus);
}
