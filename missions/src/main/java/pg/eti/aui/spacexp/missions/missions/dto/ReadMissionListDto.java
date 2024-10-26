package pg.eti.aui.spacexp.missions.missions.dto;

import lombok.Value;
import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class ReadMissionListDto {
    @Value
    private static class MissionDto {
        UUID id;
        String name;
    }

    List<MissionDto> missions;

    public static ReadMissionListDto from(List<UUID> missionIds, List<String> missionNames) {
        List<MissionDto> missions = new ArrayList<>(missionIds.size());
        for (int i = 0; i < missionIds.size(); i++) {
            missions.add(new MissionDto(missionIds.get(i), missionNames.get(i)));
        }
        return new ReadMissionListDto(missions);
    }

    public static ReadMissionListDto from(List<Mission> missions) {
        return new ReadMissionListDto(missions.stream()
                .map(mission -> new MissionDto(mission.getId(), mission.getName()))
                .toList());
    }
}
