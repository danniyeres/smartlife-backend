package org.example.smartlifebackend.repository;

import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;
import org.example.smartlifebackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}
