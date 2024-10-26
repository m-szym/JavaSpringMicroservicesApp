package pg.eti.aui.spacexp.targets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.aui.spacexp.targets.entity.Target;
import pg.eti.aui.spacexp.targets.repository.TargetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TargetDefService implements TargetService {
    private final TargetRepository targetRepository;

    @Autowired
    public TargetDefService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }


    @Override
    public void create(Target target) {
        if (targetRepository.existsById(target.getId())) {
            throw new IllegalArgumentException("Target with given id already exists");
        }
        targetRepository.save(target);
    }

    @Override
    public Optional<Target> find(UUID uuid) {
        return targetRepository.findById(uuid);
    }

    @Override
    public Optional<Target> findByName(String name) {
        return targetRepository.findByName(name);
    }

    @Override
    public List<Target> findAll() {
        return targetRepository.findAll();
    }

    @Override
    public List<Target> findAllByDistance(int distance) {
        return targetRepository.findAllByDistance(distance);
    }

    @Override
    public void update(Target target) {
        if (!targetRepository.existsById(target.getId())) {
            throw new IllegalArgumentException("Target with given id does not exist");
        }
        targetRepository.save(target);
    }

    @Override
    public void delete(UUID uuid) {
        if (!targetRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Target with given id does not exist");
        }
        targetRepository.deleteById(uuid);
    }
}
