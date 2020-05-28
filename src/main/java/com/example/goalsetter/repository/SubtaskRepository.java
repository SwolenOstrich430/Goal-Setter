package com.example.goalsetter.repository;

import com.example.goalsetter.pojo.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findByGoalId(Long goalId);
}
