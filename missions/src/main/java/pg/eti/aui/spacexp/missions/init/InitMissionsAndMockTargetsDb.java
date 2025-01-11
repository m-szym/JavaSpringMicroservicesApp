package pg.eti.aui.spacexp.missions.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;
import pg.eti.aui.spacexp.missions.missions.service.MissionService;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.service.MockTargetService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class InitMissionsAndMockTargetsDb implements InitializingBean {
    private final MissionService missionService;
    private final MockTargetService targetService;

    @Autowired
    public InitMissionsAndMockTargetsDb(MissionService missionService, MockTargetService targetService) {
        this.missionService = missionService;
        this.targetService = targetService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (targetService.findAll().isEmpty()) {
            List<MockTarget> targets = getMockTargets();
            targets.forEach(targetService::create);
        }

        if (missionService.findAll().isEmpty()) {
            List<MockTarget> targets = targetService.findAll();
            List<Mission> missions = getMockMissions(targets);
            missions.forEach(missionService::create);
        }
    }

    public static List<Mission> getMockMissions(List<MockTarget> targets) {
        return List.of(
            Mission.builder()
                    .id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b00"))
                    .name("Apollo 11")
                    .lunchDate(LocalDate.of(1970, 8, 13))
                    .arrivalDate(LocalDate.of(1972, 1, 26))
                    .target(targets.get(0))
                    .build(),
            Mission.builder()
                    .id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b01"))
                    .name("Apollo 12")
                    .lunchDate(LocalDate.of(1980, 5, 7))
                    .arrivalDate(LocalDate.of(1989, 11, 1))
                    .target(targets.get(1))
                    .build(),
            Mission.builder()
                    .id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b02"))
                    .name("Apollo 13")
                    .lunchDate(LocalDate.of(1981, 5, 7))
                    .arrivalDate(LocalDate.of(1989, 11, 1))
                    .target(targets.get(2))
                    .build(),
            Mission.builder()
                    .id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b03"))
                    .name("Apollo 14")
                    .lunchDate(LocalDate.of(1982, 5, 7))
                    .arrivalDate(LocalDate.of(1989, 11, 1))
                    .target(targets.get(0))
                    .build()
        );
    }

    public static List<MockTarget> getMockTargets() {
        return List.of(
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b00")).build(),
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b10")).build(),
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b20")).build()
        );
    }
}
