package pg.eti.aui.spacexp.targets.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.eti.aui.spacexp.targets.targets.entity.Target;
import pg.eti.aui.spacexp.targets.targets.service.TargetService;

import java.util.List;
import java.util.UUID;

@Component
public class InitTargetsDb implements InitializingBean {
    private final TargetService targetService;

    @Autowired
    public InitTargetsDb(TargetService targetService) {
        this.targetService = targetService;
    }

    @Override
    public void afterPropertiesSet() {
        List<Target> targets = getTargetList();
        targets.forEach(targetService::create);
    }

    public static List<Target> getTargetList() {
        return List.of(
                Target.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b00")).name("Luna").distance(100).build(),
                Target.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b10")).name("Mars").distance(200).build(),
                Target.builder().id(UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b20")).name("Venus").distance(300).build()
        );
    }
}
