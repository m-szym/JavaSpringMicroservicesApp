package pg.eti.aui.spacexp.targets.dto;

import lombok.Value;
import pg.eti.aui.spacexp.targets.entity.Target;

import java.util.UUID;

@Value
public class CreateTargetDto {
    String name;
    int distance;
}
