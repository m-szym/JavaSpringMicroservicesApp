package pg.eti.aui.spacexp.missions.mocktargets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MockTargetRepository extends JpaRepository<MockTarget, UUID> {
    Optional<MockTarget> findByName(String name);
}
