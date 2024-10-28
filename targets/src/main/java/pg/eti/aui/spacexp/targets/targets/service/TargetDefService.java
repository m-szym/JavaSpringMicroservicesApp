package pg.eti.aui.spacexp.targets.targets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pg.eti.aui.spacexp.targets.targets.entity.Target;
import pg.eti.aui.spacexp.targets.targets.repository.TargetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TargetDefService implements TargetService {
    private final TargetRepository targetRepository;
    private final RestTemplate template;

    @Autowired
    public TargetDefService(TargetRepository targetRepository, RestTemplate template) {
        this.targetRepository = targetRepository;
        this.template = template;
    }


    @Override
    public void create(Target target) {
        if (targetRepository.existsById(target.getId())) {
            throw new IllegalArgumentException("Target with given id already exists");
        }
        targetRepository.save(target);
    }

    @Override
    public void sendRemoteCreateEvent(Target target) {
        String url = "http://localhost:8084/api/missions/targets/" + target.getId();
        template.postForObject(url, null, Void.class);
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

    @Override
    public void sendRemoteDeleteEvent(UUID uuid) {
        String url = "http://localhost:8084/api/missions/targets/" + uuid;
        template.delete(url);
    }
}
