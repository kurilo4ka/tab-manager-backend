package com.manager.tab.tabmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tabs",
        uniqueConstraints = {})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", length = 50, nullable = false)
    private String title;

    @Column(name="artist", length = 50, nullable = false)
    private String artist;

    @Column(name="bpm", length = 3, nullable = true)
    private Integer bpm;

    @Column(name="tuning", length = 50, nullable = false)
    private String tuning;
}
