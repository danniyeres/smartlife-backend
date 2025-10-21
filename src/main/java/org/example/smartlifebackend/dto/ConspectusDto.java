package org.example.smartlifebackend.dto;

import lombok.*;
import org.example.smartlifebackend.model.Conspectus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConspectusDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public Conspectus toEntity() {
        return Conspectus.builder()
                .id(id)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }

    public static ConspectusDto fromEntity(Conspectus conspectus) {
        return ConspectusDto.builder()
                .id(conspectus.getId())
                .title(conspectus.getTitle())
                .content(conspectus.getContent())
                .createdAt(conspectus.getCreatedAt())
                .build();
    }
}
