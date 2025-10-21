package org.example.smartlifebackend.dto;

import lombok.*;
import org.example.smartlifebackend.model.Folder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolderDto {
    private Long id;
    private String folderName;
    private List<ConspectusDto> conspectusList;

    public static FolderDto fromEntity(Folder folder) {
        return FolderDto.builder()
                .id(folder.getId())
                .folderName(folder.getFolderName())
                .conspectusList(
                        folder.getConspectusList() == null
                                ? new ArrayList<>()
                                : folder.getConspectusList().stream()
                                .map(ConspectusDto::fromEntity)
                                .toList()
                )
                .build();
    }

}
