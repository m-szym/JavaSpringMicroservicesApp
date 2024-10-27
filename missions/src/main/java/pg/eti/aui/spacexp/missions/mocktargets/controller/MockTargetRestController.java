package pg.eti.aui.spacexp.missions.mocktargets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.eti.aui.spacexp.missions.mocktargets.entity.MockTarget;
import pg.eti.aui.spacexp.missions.mocktargets.service.MockTargetService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/missions/targets")
public class MockTargetRestController {
    private final MockTargetService targetService;

    @Autowired
    public MockTargetRestController(MockTargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> createMockTarget(@PathVariable UUID id) {
        try {
            targetService.create(new MockTarget(id));
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMockTarget(@PathVariable UUID id) {
        try {
            targetService.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // for DEBUG
    @GetMapping
    public List<UUID> getAllMockTargets() {
        return targetService.findAll().stream().map(MockTarget::getId).toList();
    }
}
