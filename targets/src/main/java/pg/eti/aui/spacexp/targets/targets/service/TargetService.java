package pg.eti.aui.spacexp.targets.targets.service;

import pg.eti.aui.spacexp.targets.targets.entity.Target;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TargetService {
    void create(Target target) throws IllegalArgumentException;
    void sendRemoteCreateEvent(Target target);

    Optional<Target> find(UUID uuid);
    Optional<Target> findByName(String name);
    List<Target> findAll();
    List<Target> findAllByDistance(int distance);

    void update(Target target) throws IllegalArgumentException;

    void delete(UUID uuid) throws IllegalArgumentException;
    void sendRemoteDeleteEvent(UUID uuid);
}
