package org.example.smartlifebackend.dto;

import lombok.*;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;
import org.example.smartlifebackend.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private LocalDate date;
    private Status status;
    private Priority priority;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;

    public static TaskDto fromEntity(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .date(task.getDate())
                .status(task.getStatus())
                .priority(task.getPriority())
                .description(task.getDescription())
                .imageUrl(task.getImageUrl())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public Task toEntity() {
        return Task.builder()
                .id(id)
                .title(title)
                .date(date)
                .status(status)
                .priority(priority)
                .description(description)
                .imageUrl(imageUrl)
                .createdAt(createdAt)
                .build();
    }
}
