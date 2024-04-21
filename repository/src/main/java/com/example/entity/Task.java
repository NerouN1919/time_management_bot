package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Task extends BaseEntity {
    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date_out")
    private LocalDate dateOut;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
