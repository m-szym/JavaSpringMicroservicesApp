package pg.eti.aui.spacexp.missions.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;
import pg.eti.aui.spacexp.missions.missions.service.MissionService;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.service.MockTargetService;

import java.util.List;
import java.util.UUID;

@Component
public class InitMissionsDb implements InitializingBean {
    private final MissionService missionService;
    private final MockTargetService targetService;

    @Autowired
    public InitMissionsDb(MissionService missionService, MockTargetService targetService) {
        this.missionService = missionService;
        this.targetService = targetService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<MockTarget> availableTargets = targetService.findAll();
        List<Mission> missions = getMockMissions(availableTargets);
        missions.forEach(missionService::create);
    }

    public static List<Mission> getMockMissions(List<MockTarget> targets) {
        return List.of(
            Mission.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b00")).name("Apollo 11").target(targets.get(0)).build(),
            Mission.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b01")).name("Apollo 12").target(targets.get(1)).build(),
            Mission.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b02")).name("Apollo 13").target(targets.get(2)).build(),
            Mission.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b03")).name("Apollo 14").target(targets.get(0)).build()
        );
    }
}
