package pg.eti.aui.spacexp.missions.missions.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UpdateMissionDto {
    String name;
    LocalDate lunchDate;
    LocalDate arrivalDate;
}
