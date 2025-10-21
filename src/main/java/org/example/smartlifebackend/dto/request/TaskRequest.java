package org.example.smartlifebackend.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequest {

    private String title;

    private String description;

    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate date;

    private Status status; // IN_PROGRESS, COMPLETED, NOT_STARTED

    private Priority priority; // LOW, MEDIUM, HIGH

    private String imageUrl;
}
