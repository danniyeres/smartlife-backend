package org.example.smartlifebackend.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequest {

    private String title;
    private String desc;
    private String priority; // Extreme | Moderate | Low
    private String status;   // Not Started | In Progress | Completed
    private LocalDateTime deadline; // iso 8601 format
    private String coverImage; // url
}
