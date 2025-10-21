package org.example.smartlifebackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStatsDto {
    private long total;
    private long completed;
    private long inProgress;
    private long notStarted;

    private double completedPercent;
    private double inProgressPercent;
    private double notStartedPercent;
}
