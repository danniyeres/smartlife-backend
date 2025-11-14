package org.example.smartlifebackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartlifebackend.dto.TaskDto;

import org.example.smartlifebackend.dto.request.TaskRequest;
import org.example.smartlifebackend.model.Priority;
import org.example.smartlifebackend.model.Status;
import org.example.smartlifebackend.model.Task;

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

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .desc(taskRequest.getDesc())
                .priority(Priority.valueOf(taskRequest.getPriority().toUpperCase().replace(" ", "_")))
                .status(Status.valueOf(taskRequest.getStatus().toUpperCase().replace(" ", "_")))
                .coverImage(taskRequest.getCoverImage())
                .deadline(taskRequest.getDeadline())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        log.info("Task added: {} for user: {}", savedTask.getTitle(), user.getUsername());
        return TaskDto.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .desc(savedTask.getDesc())
                .priority(savedTask.getPriority().getDisplayName())
                .status(savedTask.getStatus().getDisplayName())
                .deadline(savedTask.getDeadline())
                .coverImage(savedTask.getCoverImage())
                .accent(savedTask.getAccent())
                .completedAt(savedTask.getCompletedAt())
                .build();
    }

    public void deleteTask(Long taskId) {
        log.info("Task deleted with id: {}", taskId);
        taskRepository.deleteById(taskId);
    }


    public TaskDto updateTask(Long taskId, TaskRequest taskRequest) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (taskRequest.getTitle() != null) task.setTitle(taskRequest.getTitle());
        if (taskRequest.getDesc() != null) task.setDesc(taskRequest.getDesc());
        if (taskRequest.getPriority() != null)
            task.setPriority(Priority.valueOf(taskRequest.getPriority().toUpperCase().replace(" ", "_")));
        if (taskRequest.getStatus() != null)
            task.setStatus(Status.valueOf(taskRequest.getStatus().toUpperCase().replace(" ", "_")));
        if (taskRequest.getCoverImage() != null) task.setCoverImage(taskRequest.getCoverImage());
        if (taskRequest.getDeadline() != null) task.setDeadline(taskRequest.getDeadline());


        taskRepository.save(task);

        log.info("Task updated: {} with id: {}", task.getTitle(), task.getId());
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .desc(task.getDesc())
                .priority(task.getPriority().getDisplayName())
                .status(task.getStatus().getDisplayName())
                .deadline(task.getDeadline())
                .coverImage(task.getCoverImage())
                .accent(task.getAccent())
                .completedAt(task.getCompletedAt())
                .build();
    }


    public TaskDto getTaskById(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .desc(task.getDesc())
                .priority(task.getPriority().getDisplayName())
                .status(task.getStatus().getDisplayName())
                .deadline(task.getDeadline())
                .coverImage(task.getCoverImage())
                .accent(task.getAccent())
                .completedAt(task.getCompletedAt())
                .build();
    }

    public List<TaskDto> getAllTasksOfUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(task -> TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .desc(task.getDesc())
                .priority(task.getPriority().getDisplayName())
                .status(task.getStatus().getDisplayName())
                .deadline(task.getDeadline())
                .coverImage(task.getCoverImage())
                .accent(task.getAccent())
                .completedAt(task.getCompletedAt())
                .build()).toList();
    }


}
