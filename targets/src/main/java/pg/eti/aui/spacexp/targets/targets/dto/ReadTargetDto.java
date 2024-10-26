package pg.eti.aui.spacexp.targets.targets.dto;

import lombok.Value;
import pg.eti.aui.spacexp.targets.targets.entity.Target;

import java.util.UUID;

@Value
public class ReadTargetDto {
    UUID id;
    String name;
    int distance;

   public static ReadTargetDto from(Target target) {
        return new ReadTargetDto(target.getId(), target.getName(), target.getDistance());
    }
}
