package pg.eti.aui.spacexp.missions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import pg.eti.aui.spacexp.missions.dto.CreateMissionDto;
import pg.eti.aui.spacexp.missions.dto.ReadMissionListDto;
import pg.eti.aui.spacexp.missions.entity.Mission;
import pg.eti.aui.spacexp.missions.service.MissionService;
import pg.eti.aui.spacexp.targets.entity.Target;
import pg.eti.aui.spacexp.targets.service.TargetService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/missions")
public class MissionRestController {
    private final MissionService missionService;
    private final TargetService targetService;

    @Autowired
    public MissionRestController(MissionService missionService, TargetService targetService) {
        this.missionService = missionService;
        this.targetService = targetService;
    }

    @PostMapping
    public ResponseEntity<Void> createMission(@RequestBody @NonNull CreateMissionDto newMissionDto) {
        Optional<Target> target = targetService.findByName(newMissionDto.getTargetName());
        if (target.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mission newMission = Mission.builder()
                .id(UUID.randomUUID())
                .name(newMissionDto.getName())
                .target(target.get())
                .arrivalDate(newMissionDto.getArrivalDate())
                .lunchDate(newMissionDto.getLunchDate())
                .build();

        try {
            missionService.create(newMission);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ReadMissionListDto> getMissions() {
        return ResponseEntity.ok(ReadMissionListDto.from(missionService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMission(@PathVariable UUID id) {
        Optional<Mission> mission = missionService.find(id);
        return mission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/target/{targetId}")
    public ResponseEntity<ReadMissionListDto> getMissionsByTarget(@PathVariable UUID targetId) {
        try {
            return ResponseEntity.ok(ReadMissionListDto.from(missionService.findAllByTargetId(targetId)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMission(@PathVariable UUID id, @RequestBody @NonNull CreateMissionDto updatedMissionDto) {
        Optional<Mission> mission = missionService.find(id);
        if (mission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mission updatedMission = Mission.builder()
                .id(id)
                .name(updatedMissionDto.getName())
                .target(mission.get().getTarget())
                .arrivalDate(updatedMissionDto.getArrivalDate())
                .lunchDate(updatedMissionDto.getLunchDate())
                .build();

        try {
            missionService.update(updatedMission);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable UUID id) {
        Optional<Mission> mission = missionService.find(id);
        if (mission.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            missionService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

}
