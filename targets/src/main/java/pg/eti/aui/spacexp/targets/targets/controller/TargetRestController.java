package pg.eti.aui.spacexp.targets.targets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.eti.aui.spacexp.targets.targets.dto.CreateTargetDto;
import pg.eti.aui.spacexp.targets.targets.dto.ReadTargetDto;
import pg.eti.aui.spacexp.targets.targets.dto.ReadTargetListDto;
import pg.eti.aui.spacexp.targets.targets.dto.UpdateTargetDto;
import pg.eti.aui.spacexp.targets.targets.entity.Target;
import pg.eti.aui.spacexp.targets.targets.service.TargetService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/targets")
public class TargetRestController {
    private final TargetService targetService;

    @Autowired
    public TargetRestController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping
    public ResponseEntity<Void> createTarget(@RequestBody CreateTargetDto newTargetDto) {
        try {
            targetService.create(Target.builder()
                    .id(UUID.randomUUID())
                    .name(newTargetDto.getName())
                    .distance(newTargetDto.getDistance())
                    .build());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ReadTargetListDto> getAllTargets() {
        return ResponseEntity.ok(ReadTargetListDto.from(targetService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadTargetDto> getTarget(@PathVariable("id") UUID id) {
        return targetService.find(id)
                .map(target -> ResponseEntity.ok(ReadTargetDto.from(target)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTarget(@PathVariable("id") UUID id, @RequestBody UpdateTargetDto updateInfo) {
        Optional<Target> target = targetService.find(id);
        if (target.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (updateInfo.getName() != null)
            target.get().setName(updateInfo.getName());
        if (updateInfo.getDistance() != 0)
            target.get().setDistance(updateInfo.getDistance());

        try {
            targetService.update(target.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable("id") UUID id) {
        Optional<Target> target = targetService.find(id);
        if (target.isPresent()) {
            targetService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

