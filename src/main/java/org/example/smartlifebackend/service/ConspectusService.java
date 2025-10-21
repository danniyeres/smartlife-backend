package org.example.smartlifebackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartlifebackend.dto.ConspectusDto;
import org.example.smartlifebackend.dto.FolderDto;
import org.example.smartlifebackend.dto.request.ConspectusRequest;
import org.example.smartlifebackend.model.Conspectus;
import org.example.smartlifebackend.model.Folder;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.repository.ConspectusRepository;
import org.example.smartlifebackend.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConspectusService {
    private final ConspectusRepository conspectusRepository;
    private final FolderRepository folderRepository;


    public ConspectusDto createConspectus(User user, ConspectusRequest conspectusRequest) {
        Folder folder = null;
        if (conspectusRequest.getFolderId() != null) {
            List<Folder> folders = folderRepository.findByUser(user);
            for (Folder f : folders) {
                if (f.getId().equals(conspectusRequest.getFolderId())) {
                    folder = f;
                    break;
                }
            }
        }

        Conspectus c = Conspectus.builder()
                .title(conspectusRequest.getTitle())
                .content(conspectusRequest.getContent())
                .user(user)
                .folder(folder)
                .build();

        Conspectus savedConspectus = conspectusRepository.save(c);
        log.info("Conspectus saved: {}", savedConspectus);
        return ConspectusDto.fromEntity(savedConspectus);
    }

    public FolderDto createFolder(User user, String folderName) {
        Folder folder = Folder.builder()
                .folderName(folderName)
                .user(user)
                .build();

        Folder savedFolder = folderRepository.save(folder);
        log.info("Folder saved: {}", savedFolder);
        return FolderDto.fromEntity(savedFolder);

    }

    public ConspectusDto moveConspectusToFolder(User user, Long conspectusId, Long folderId) {
        Conspectus conspectus = conspectusRepository.findById(conspectusId)
                .orElseThrow(() -> new RuntimeException("Conspectus not found"));

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        if (!conspectus.getUser().getId().equals(user.getId()) || !folder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        conspectus.setFolder(folder);
        Conspectus updatedConspectus = conspectusRepository.save(conspectus);
        log.info("Conspectus moved to folder: {}", updatedConspectus);
        return ConspectusDto.fromEntity(updatedConspectus);
    }

    public List<ConspectusDto> getAllByUser(User user) {
        List<Conspectus> conspectuses = conspectusRepository.findByUser(user);
        return conspectuses.stream().map(ConspectusDto::fromEntity).toList();
    }

    public List<FolderDto> getAllFoldersByUser(User user) {
        List<Folder> folders = folderRepository.findByUser(user);
        return folders.stream().map(FolderDto::fromEntity).toList();
    }

    public List<ConspectusDto> getAllByFolderId(Long folderId) {
        List<Conspectus> conspectuses = conspectusRepository.findByFolderId(folderId);
        return conspectuses.stream().map(ConspectusDto::fromEntity).toList();
    }
}
