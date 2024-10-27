package pg.eti.aui.spacexp.missions.mocktargets.entity;

import jakarta.persistence.*;
import lombok.*;

import pg.eti.aui.spacexp.missions.missions.entity.Mission;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "mock_targets")
public class MockTarget implements Serializable {
    @Id
    private UUID id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE)
    private List<Mission> missions;

    public MockTarget(UUID id) {
        this.id = id;
        this.missions = List.of();
    }
}
