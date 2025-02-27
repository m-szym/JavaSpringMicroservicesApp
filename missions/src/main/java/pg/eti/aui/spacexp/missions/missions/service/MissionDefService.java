package pg.eti.aui.spacexp.missions.missions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pg.eti.aui.spacexp.missions.missions.entity.Mission;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.repository.MockTargetRepository;
import pg.eti.aui.spacexp.missions.missions.repository.MissionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MissionDefService implements MissionService {
    private final MissionRepository pcRepo;
    private final MockTargetRepository profRepo;

    @Autowired
    public MissionDefService(MissionRepository pcRepo, MockTargetRepository profRepo) {
        this.pcRepo = pcRepo;
        this.profRepo = profRepo;
    }

    @Override
    public void create(Mission character) {
        if (pcRepo.existsById(character.getId())) {
            throw new IllegalArgumentException("Mission with given id already exists");
        }
        pcRepo.save(character);
    }

    @Override
    public Optional<Mission> find(UUID uuid) {
        return pcRepo.findById(uuid);
    }

    @Override
    public Optional<Mission> findByName(String name) {
        return pcRepo.findByName(name);
    }

    @Override
    public Optional<List<Mission>> findAllByMission(MockTarget target) {
        return profRepo.findById(target.getId()).map(pcRepo::findAllByTarget);
    }

    @Override
    public List<Mission> findAll() {
        return pcRepo.findAll();
    }

    @Override
    public List<Mission> findAllByLunchDate(LocalDate lunchDate) {
        return pcRepo.findAllByLunchDate(lunchDate);
    }

    @Override
    public List<Mission> findAllByArrivalDate(LocalDate arrivalDate) {
        return pcRepo.findAllByArrivalDate(arrivalDate);
    }

    @Override
    public List<Mission> findAllByTarget(MockTarget target) {
        Optional<MockTarget> foundTarget = profRepo.findById(target.getId());
        if (foundTarget.isEmpty()) {
            throw new IllegalArgumentException("MockTarget with given id does not exist");
        }

        return pcRepo.findAllByTarget(foundTarget.get());
    }

    @Override
    public List<Mission> findAllByTargetId(UUID targetId) {
        Optional<MockTarget> foundTarget = profRepo.findById(targetId);
        if (foundTarget.isEmpty()) {
            throw new IllegalArgumentException("MockTarget with given id does not exist");
        }

        return pcRepo.findAllByTarget(foundTarget.get());
    }

    @Override
    public void update(Mission character) {
        if (!pcRepo.existsById(character.getId())) {
            throw new IllegalArgumentException("Mission with given id does not exist");
        }
        pcRepo.save(character);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        if (!pcRepo.existsById(uuid)) {
            throw new IllegalArgumentException("Mission with given id does not exist");
        }
         pcRepo.findById(uuid).ifPresent(pcRepo::delete);
    }
}
