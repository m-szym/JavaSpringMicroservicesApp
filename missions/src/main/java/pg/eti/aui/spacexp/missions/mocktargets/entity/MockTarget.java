package org.example.missions.mocktargets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import org.example.missions.missions.entity.Mission;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "mock_targets")
public class MockTarget {
    @Id
    private UUID id;

    private String name;

    private List<Mission> missions;
}
