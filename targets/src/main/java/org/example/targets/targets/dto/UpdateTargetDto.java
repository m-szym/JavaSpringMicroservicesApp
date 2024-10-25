package pg.eti.aui.spacexp.targets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
@Builder
public class UpdateTargetDto implements Serializable {
    int distance;
    String name;
}
