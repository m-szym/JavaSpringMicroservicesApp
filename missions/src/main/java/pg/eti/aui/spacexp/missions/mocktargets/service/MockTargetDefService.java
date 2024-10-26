package pg.eti.aui.spacexp.missions.mocktargets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.repository.MockTargetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MockTargetDefService implements MockTargetService {
    private final MockTargetRepository mockRepo;

    @Autowired
    public MockTargetDefService(MockTargetRepository mockRepo) {
        this.mockRepo = mockRepo;
    }

    public Optional<MockTarget> find(UUID id) {
        return mockRepo.findById(id);
    }

    public Optional<MockTarget> findByName(String name) {
        return mockRepo.findByName(name);
    }

    public List<MockTarget> findAll() {
        return mockRepo.findAll();
    }

    public void create(MockTarget mockTarget) {
        if (mockRepo.existsById(mockTarget.getId())) {
            throw new IllegalArgumentException("MockTarget with given id already exists");
        }
        mockRepo.save(mockTarget);
    }

    public void update(MockTarget mockTarget) {
        if (!mockRepo.existsById(mockTarget.getId())) {
            throw new IllegalArgumentException("MockTarget with given id does not exist");
        }
        mockRepo.save(mockTarget);
    }

    public void delete(MockTarget mockTarget) {
        mockRepo.delete(mockTarget);
    }

    public void delete(UUID id) {
        mockRepo.deleteById(id);
    }


}
