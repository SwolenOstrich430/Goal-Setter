package com.example.goalsetter.service;

import com.example.goalsetter.pojo.Goal;
import com.example.goalsetter.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }

    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }
}
