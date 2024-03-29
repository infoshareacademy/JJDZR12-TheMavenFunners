package com.isa.tasktrackerwebapp.repository;

import com.isa.tasktrackerwebapp.model.entity.Task;
import com.isa.tasktrackerwebapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByTaskStartAsc();
    List<Task> findAllByOrderByTaskStartDesc();
    List<Task> findAllByOrderByTaskEndAsc();
    List<Task> findAllByOrderByTaskEndDesc();
    List<Task> findAllByUserAndActiveOrderByTaskEndAsc(User user, boolean active);
    Task findTaskById(Long id);
}
