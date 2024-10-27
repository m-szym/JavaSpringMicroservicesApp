package pg.eti.aui.spacexp.missions.mocktargets.service;

import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MockTargetService {
    void create(MockTarget mockTarget);

    Optional<MockTarget> find(UUID uuid);

    List<MockTarget> findAll();

    void update(MockTarget mockTarget);

    void delete(UUID uuid);
}
