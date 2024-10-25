package pg.eti.aui.spacexp.missions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder;
import pg.eti.aui.spacexp.targets.entity.Target;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "exploration_missions")
public class Mission implements Serializable {
    @Id
    private UUID id;

    @Column(name = "mission_name", unique = true)
    private String name;

    @Column(name = "mission_lunch_date")
    private LocalDate lunchDate;

    @Column(name = "mission_arrival_date")
    private LocalDate arrivalDate;

    @EqualsAndHashCode.Exclude
    @ManyToOne @JoinColumn(name = "mission_target")
    private Target target;

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id + '\n' +
                ", name='" + name + '\n' +
                ", target=" + target.getName() + '\n' +
                ", lunchDate=" + lunchDate + '\n' +
                ", arrivalDate=" + arrivalDate + '\n' +
                '}';
    }

    public void setTarget(Target target) {
        this.target = target;
        if (!target.getMissions().contains(this)) {
            target.getMissions().add(this);
        }
    }
}
