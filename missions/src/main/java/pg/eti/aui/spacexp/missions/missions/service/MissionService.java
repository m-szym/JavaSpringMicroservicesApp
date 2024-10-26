package pg.eti.aui.spacexp.missions.missions.service;

import pg.eti.aui.spacexp.missions.missions.entity.Mission;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionService {
    void create(Mission character) throws IllegalArgumentException;

    Optional<Mission> find(UUID uuid);
    Optional<Mission> findByName(String name);
    Optional<List<Mission>> findAllByMission(MockTarget target);
    List<Mission> findAll();
    List<Mission> findAllByLunchDate(LocalDate lunchDate);
    List<Mission> findAllByArrivalDate(LocalDate arrivalDate);
    List<Mission> findAllByTarget(MockTarget target);
    List<Mission> findAllByTargetId(UUID targetId);

    void update(Mission character) throws IllegalArgumentException;

    void delete(UUID uuid) throws IllegalArgumentException;
}
