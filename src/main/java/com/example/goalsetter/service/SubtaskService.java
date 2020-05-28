package com.example.goalsetter.service;

import com.example.goalsetter.pojo.Subtask;
import com.example.goalsetter.repository.SubtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;

    public List<Subtask> getSubtasksByGoalId(Long goalId) {
        return subtaskRepository.findByGoalId(goalId);
    }

    public List<Subtask> getAllSubtasks() {
        return subtaskRepository.findAll();
    }
}
