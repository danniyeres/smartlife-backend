package org.example.smartlifebackend.dto;

import lombok.*;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;
import org.example.smartlifebackend.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String desc;
    private String priority; // Extreme | Moderate | Low
    private String status;   // Not Started | In Progress | Completed
    private LocalDateTime deadline;
    private String coverImage;
    private String accent;
    private OffsetDateTime completedAt;

}
