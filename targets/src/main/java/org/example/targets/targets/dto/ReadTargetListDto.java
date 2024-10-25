package pg.eti.aui.spacexp.targets.dto;


import lombok.Value;
import pg.eti.aui.spacexp.targets.entity.Target;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Value
public class ReadTargetListDto {
    @Value
    private static class TargetDto {
        UUID id;
        String name;
    }

    List<TargetDto> targets;

    public static ReadTargetListDto from(List<UUID> targetIds, List<String> targetNames) {
        List<TargetDto> targets = new ArrayList<>(targetIds.size());
        for (int i = 0; i < targetIds.size(); i++) {
            targets.add(new TargetDto(targetIds.get(i), targetNames.get(i)));
        }
        return new ReadTargetListDto(targets);
    }

    public static ReadTargetListDto from(List<Target> targets) {
        return new ReadTargetListDto(targets.stream()
                .map(target -> new TargetDto(target.getId(), target.getName()))
                .toList());
    }
}
