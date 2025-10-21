package org.example.smartlifebackend.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConspectusRequest {
    private String title;
    private String content;
    private Long folderId;
}
