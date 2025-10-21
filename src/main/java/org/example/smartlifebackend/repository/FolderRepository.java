package org.example.smartlifebackend.repository;

import org.example.smartlifebackend.model.Folder;
import org.example.smartlifebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByUser(User user);
}
