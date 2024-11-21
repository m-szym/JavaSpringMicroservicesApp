package pg.eti.aui.spacexp.missions.missions.dto;

import lombok.Value;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class ReadMissionDto {
    UUID id;
    String name;
    LocalDate lunchDate;
    LocalDate arrivalDate;
    UUID targetId;

   public static ReadMissionDto from(Mission mission) {
        return new ReadMissionDto(
                mission.getId(),
                mission.getName(),
                mission.getLunchDate(),
                mission.getArrivalDate(),
                mission.getTarget().getId()
        );
    }
}
