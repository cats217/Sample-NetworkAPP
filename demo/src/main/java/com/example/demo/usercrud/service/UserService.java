package com.example.demo.usercrud.service;

import java.util.List;

import com.example.demo.usercrud.domain.User;
import com.example.demo.usercrud.repository.interfaces.UserDaoIF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    @Qualifier("UserDaoJdbc")

    UserDaoIF dao;

    // 全件取得
    public List<User> selectAll() {
        return dao.selectAll();
    }

    // 1件取得
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    // 一件追加＆結果(True/False)の返却
    public boolean insert(User user) {
        int dbResult = dao.insertOne(user);
        return judgeDbOperation(dbResult);
    }

    // 1件更新＆結果(True/False)の返却
    public boolean updateOne(User user) {
        int dbResult = dao.updateOne(user);
        return judgeDbOperation(dbResult);
    }

    // 1件削除＆結果(True/False)の返却
    public boolean deleteOne(String userId) {
        int dbResult = dao.deleteOne(userId);
        return judgeDbOperation(dbResult);
    }

    // DB操作判定(T=処理成功,F=処理失敗)
    protected boolean judgeDbOperation(int target) {

        if (target >= 1) {
            return true;
        }
        return false;
    }
}
