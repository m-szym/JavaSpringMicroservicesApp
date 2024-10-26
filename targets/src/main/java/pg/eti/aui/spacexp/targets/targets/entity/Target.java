package pg.eti.aui.spacexp.targets.targets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
}
