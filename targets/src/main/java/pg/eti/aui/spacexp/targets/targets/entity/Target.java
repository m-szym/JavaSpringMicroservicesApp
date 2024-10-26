package pg.eti.aui.spacexp.targets.entity;

import jakarta.persistence.*;
import lombok.*;
import pg.eti.aui.spacexp.missions.entity.Mission;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exploaration_target")
public class Target implements Serializable {
    @Id
    private UUID id;

    @Column(name = "target_name", unique = true)
    private String name;

    @Column(name = "target_distance")
    private int distance;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mission> missions;
}
