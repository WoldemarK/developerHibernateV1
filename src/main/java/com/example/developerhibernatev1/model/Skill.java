package com.example.developerhibernatev1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@ToString(exclude = "developers")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "skillID"),
            inverseJoinColumns = @JoinColumn(name = "developerID"))
    private List<Developer> developers;

    public Skill(Long id) {
        this.id = id;
    }

    public void addDeveloperToSkill(Developer developer) {
        if (this.developers == null) {
            this.developers = new ArrayList<>();
        }
        this.developers.add(developer);
    }
}
