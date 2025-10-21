package org.example.smartlifebackend.repository;

import org.example.smartlifebackend.model.Conspectus;
import org.example.smartlifebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConspectusRepository extends JpaRepository<Conspectus, Long> {
    List<Conspectus> findByUser(User user);
    List<Conspectus> findByFolderId(Long folderId);
}
