package pg.eti.aui.spacexp.targets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.eti.aui.spacexp.targets.entity.Target;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TargetRepository extends JpaRepository<Target, UUID> {
    Optional<Target> findByName(String name);
    List<Target> findAllByDistance(int distance);
}
