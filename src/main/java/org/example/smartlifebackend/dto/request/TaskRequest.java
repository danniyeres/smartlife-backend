package org.example.smartlifebackend.dto.request;


import lombok.*;



import java.time.LocalDateTime;

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
