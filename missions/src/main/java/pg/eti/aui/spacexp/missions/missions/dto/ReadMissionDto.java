package pg.eti.aui.spacexp.missions.missions.dto;

import lombok.Value;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.time.LocalDate;

@Value
public class ReadMissionDto {
    String name;
    LocalDate lunchDate;
    LocalDate arrivalDate;
    String targetName;

   public static ReadMissionDto from(Mission mission) {
        return new ReadMissionDto(
                mission.getName(),
                mission.getLunchDate(),
                mission.getArrivalDate(),
                mission.getTarget().getName()
        );
    }
}
