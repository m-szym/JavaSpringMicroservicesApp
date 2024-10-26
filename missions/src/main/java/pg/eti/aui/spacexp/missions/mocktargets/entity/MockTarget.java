package pg.eti.aui.spacexp.missions.mocktargets.entity;

import jakarta.persistence.*;
import lombok.*;

import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "mock_targets")
public class MockTarget {
    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE)
    private List<Mission> missions;
}
