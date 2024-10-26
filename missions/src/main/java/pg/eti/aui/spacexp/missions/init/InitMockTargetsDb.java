package pg.eti.aui.spacexp.missions.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.service.MockTargetService;

import java.util.List;
import java.util.UUID;

@Component
public class InitMockTargetsDb implements InitializingBean {
    private final MockTargetService targetService;

    @Autowired
    public InitMockTargetsDb(MockTargetService targetService) {
        this.targetService = targetService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<MockTarget> targets = getMockTargets();
        targets.forEach(targetService::create);
    }

    public static List<MockTarget> getMockTargets() {
        return List.of(
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b00")).name("Luna").build(),
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b10")).name("Mars").build(),
                MockTarget.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b20")).name("Venus").build()
        );
    }
}
