package org.example.smartlifebackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartlifebackend.dto.TaskDto;
import org.example.smartlifebackend.dto.TaskStatsDto;
import org.example.smartlifebackend.dto.request.TaskRequest;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;
import org.example.smartlifebackend.model.Task;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.repository.TaskRepository;
import org.example.smartlifebackend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDto addTask(Long userId, TaskRequest taskRequest) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (taskRequest.getStatus() == null) {
            taskRequest.setStatus(Status.NOT_STARTED);
        }

        var task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .date(taskRequest.getDate())
                .status(taskRequest.getStatus())
                .priority(taskRequest.getPriority())
                .imageUrl(taskRequest.getImageUrl())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        log.info("Task added: {} for user: {}", savedTask.getTitle(), user.getUsername());
        return TaskDto.fromEntity(savedTask);
    }

    public void deleteTask(Long taskId) {
        log.info("Task deleted with id: {}", taskId);
        taskRepository.deleteById(taskId);
    }

    public TaskDto updateTask(Long taskId, TaskRequest taskRequest) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (taskRequest.getTitle() != null) task.setTitle(taskRequest.getTitle());
        if (taskRequest.getDescription() != null) task.setDescription(taskRequest.getDescription());
        if (taskRequest.getDate() != null) task.setDate(taskRequest.getDate());
        if (taskRequest.getStatus() != null) task.setStatus(taskRequest.getStatus());
        if (taskRequest.getPriority() != null) task.setPriority(taskRequest.getPriority());
        if (taskRequest.getImageUrl() != null) task.setImageUrl(taskRequest.getImageUrl());


        taskRepository.save(task);

        log.info("Task updated: {} with id: {}", task.getTitle(), task.getId());
        return TaskDto.fromEntity(task);
    }


    public TaskDto getTaskById(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return TaskDto.fromEntity(task);
    }

    public List<TaskDto> getAllTasksOfUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(TaskDto::fromEntity).toList();
    }

    public List<TaskDto> getTasksByStatus(Long userId, Status status) {
        List<Task> tasks = taskRepository.findByUserId(userId).stream()
                .filter(task -> task.getStatus() == status)
                .toList();
        return tasks.stream().map(TaskDto::fromEntity).toList();
    }

    public List<TaskDto> getTasksByPriority(Long userId, Priority priority) {
        List<Task> tasks = taskRepository.findByUserId(userId).stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
        return tasks.stream().map(TaskDto::fromEntity).toList();
    }

    public TaskStatsDto getTaskStats(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Task> tasks = taskRepository.findByUserId(existingUser.getId());
        long total = tasks.size();

        long completed = tasks.stream()
                .filter(task -> task.getStatus() == Status.COMPLETED)
                .count();
        long inProgress = tasks.stream()
                .filter(task -> task.getStatus() == Status.IN_PROGRESS)
                .count();
        long notStarted = tasks.stream()
                .filter(task -> task.getStatus() == Status.NOT_STARTED)
                .count();

        double completedPercentage = total == 0 ? 0 : (int) ((double) completed / total * 100);
        double inProgressPercentage = total == 0 ? 0 : (int) ((double) inProgress / total * 100);
        double notStartedPercentage = total == 0 ? 0 : (int) ((double) notStarted / total * 100);


        return  new TaskStatsDto(
                total,
                completed,
                inProgress,
                notStarted,
                completedPercentage,
                inProgressPercentage,
                notStartedPercentage
        );

    }
}
