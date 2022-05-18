package com.example.demo.usercrud.controller.web;

import java.util.List;

import com.example.demo.usercrud.domain.User;
import com.example.demo.usercrud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserControllerWeb {

    @Autowired
    UserService userService;

    // User情報を全件取得
    @GetMapping
    public String selectAll(Model model) {

        String forwardName = "UserList";
        String modelKey = "users";

        model.addAttribute(modelKey, userService.selectAll());
        return forwardName;
    }

    // User情報を1件取得
    @GetMapping("{id:[0-9]+}")
    public String selectOne(Model model, @PathVariable("id") String userId) {

        String forwardName = "UserList";
        String modelKey = "users";

        model.addAttribute(modelKey, userService.selectOne(userId));
        return forwardName;
    }

    // User情報を1件追加
    @GetMapping("/newUser")
    public String newItem(Model model, @ModelAttribute("user") User user) {

        String forwardName = "NewUser";
        return forwardName;
    }
}
