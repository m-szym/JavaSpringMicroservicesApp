package pg.eti.aui.spacexp.missions.missions.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateMissionDto {
    String name;
    LocalDate lunchDate;
    LocalDate arrivalDate;
    UUID targetId;
}
