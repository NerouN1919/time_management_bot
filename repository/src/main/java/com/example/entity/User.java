package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class User extends BaseEntity {
    @Column(name = "profile_id")
    private Long profileId;
    @Column(name = "is_need_notify",
            nullable = false)
    @Builder.Default
    private Boolean isNeedNotify = true;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.NOTHING;
    @OneToMany(mappedBy = "user")
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        this.taskList.add(task);
        task.setUser(this);
    }

    public enum State {
        NOTHING,
        ADD_TASK
    }
}
