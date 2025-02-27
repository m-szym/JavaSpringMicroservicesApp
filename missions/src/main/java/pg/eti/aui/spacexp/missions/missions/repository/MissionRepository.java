package pg.eti.aui.spacexp.missions.missions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {
    Optional<Mission> findByName(String name);
    List<Mission> findAllByLunchDate(LocalDate lunchDate);
    List<Mission> findAllByArrivalDate(LocalDate arrivalDate);
    List<Mission> findAllByTarget(MockTarget target);
}
