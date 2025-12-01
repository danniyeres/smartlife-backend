package org.example.smartlifebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.dto.TaskDto;
import org.example.smartlifebackend.dto.request.TaskRequest;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.security.CurrentUser;
import org.example.smartlifebackend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<TaskDto> addTask (@CurrentUser User user, @RequestBody TaskRequest taskRequest){
        TaskDto taskDto = taskService.addTask(user.getId(), taskRequest);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping()
    public List<TaskDto> getAllTasks (@CurrentUser User user){
        return taskService.getAllTasksOfUser(user.getId());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById (@PathVariable Long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask (@PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
        TaskDto taskDto = taskService.updateTask(taskId, taskRequest);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask (@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
