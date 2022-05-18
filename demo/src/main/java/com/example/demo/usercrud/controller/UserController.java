package com.example.demo.usercrud.controller;

import java.util.List;

import com.example.demo.usercrud.domain.User;
import com.example.demo.usercrud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // User情報を全件取得
    @GetMapping("/selectAll")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    // User情報を1件取得
    @GetMapping("/selectOne/{id:[0-9]+}")
    public User selectOne(@PathVariable("id") String userId) {
        return userService.selectOne(userId);
    }

    // User情報を1件追加
    @PostMapping("/insert")
    public String insert(@RequestBody User user) {

        String dbType = "1件追加";
        boolean dbResult = userService.insert(user);

        return createDbResultMsg(dbType, dbResult);
    }

    // User情報を1件更新
    @PostMapping("/updateOne")
    public String updateOne(@RequestBody User user) {

        String dbType = "1件更新";
        boolean dbResult = userService.updateOne(user);

        return createDbResultMsg(dbType, dbResult);
    }

    // User情報を1件削除
    @PostMapping("/deleteOne/{id:.+}")
    public String deleteOne(@PathVariable("id") String userId) {

        String dbType = "1件削除";
        boolean dbResult = userService.deleteOne(userId);

        return createDbResultMsg(dbType, dbResult);
    }

    // DB操作結果のメッセージ作成
    protected String createDbResultMsg(String oprType, boolean judgeResult) {

        StringBuilder resultMsg = new StringBuilder();
        resultMsg.append(oprType);

        if (judgeResult)
            resultMsg.append("成功!");
        else
            resultMsg.append("失敗!");

        return resultMsg.toString();
    }
}
