package com.example.goalsetter.controller;

import com.example.goalsetter.error.ResourceNotFoundException;
import com.example.goalsetter.pojo.Goal;
import com.example.goalsetter.pojo.User;
import com.example.goalsetter.security.JwtUserDetails;
import com.example.goalsetter.service.GoalService;
import com.example.goalsetter.service.UserService;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Goal> getGoals(@AuthenticationPrincipal JwtUserDetails userDetails) {
        return goalService.getByUserId(userDetails.getId());
    }

    @PostMapping
    public Goal createGoal(@AuthenticationPrincipal JwtUserDetails userDetails,
                           @RequestBody Goal goal) {
        System.out.println("got in method");
        try {
            System.out.println("no error");
            Optional<User> user = userService.getUserById(userDetails.getId());
            goal.setUser(user.get());

            return goalService.createGoal(goal);
        } catch(HibernateError error) {
            throw new ResourceNotFoundException("Could not create goal", error);
        }
    }

}
