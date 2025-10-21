package org.example.smartlifebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.dto.ConspectusDto;
import org.example.smartlifebackend.dto.FolderDto;
import org.example.smartlifebackend.dto.request.ConspectusRequest;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.security.CurrentUser;
import org.example.smartlifebackend.service.ConspectusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conspectus")
public class ConspectusController {

    private final ConspectusService conspectusService;

    @PostMapping("/create")
    public ResponseEntity<ConspectusDto> create(@CurrentUser User user, @RequestBody ConspectusRequest conspectusRequest) {
        ConspectusDto createdConspectus = conspectusService.createConspectus(user, conspectusRequest);
        return ResponseEntity.ok(createdConspectus);
    }

    @PostMapping("/folder/create")
    public ResponseEntity<FolderDto> createFolder(@CurrentUser User user, @RequestBody String folderName) {
        FolderDto folderDto = conspectusService.createFolder(user, folderName);
        return ResponseEntity.ok(folderDto);
    }

    @PutMapping("/move/{conspectusId}/to/{folderId}")
    public ResponseEntity<ConspectusDto> moveToFolder(@CurrentUser User user,
                                                     @PathVariable Long conspectusId,
                                                     @PathVariable Long folderId) {
        ConspectusDto updatedConspectus = conspectusService.moveConspectusToFolder(user, conspectusId, folderId);
        return ResponseEntity.ok(updatedConspectus);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ConspectusDto>> getAllByUser(@CurrentUser User user) {
        List<ConspectusDto> conspectusList = conspectusService.getAllByUser(user);
        return ResponseEntity.ok(conspectusList);
    }

    @GetMapping("/all/{folderId}")
    public ResponseEntity<List<ConspectusDto>> getAllByFolder(@PathVariable Long folderId) {
        List<ConspectusDto> conspectusList = conspectusService.getAllByFolderId(folderId);
        return ResponseEntity.ok(conspectusList);
    }

    @GetMapping("/folders")
    public ResponseEntity<List<FolderDto>> getAllFoldersByUser(@CurrentUser User user) {
        List<FolderDto> folderList = conspectusService.getAllFoldersByUser(user);
        return ResponseEntity.ok(folderList);
    }
}
