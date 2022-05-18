package com.example.demo.usercrud.repository.interfaces;

import java.util.List;

import com.example.demo.usercrud.domain.User;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoIF {

    // Userテーブルの全データを取得
    public List<User> selectAll() throws DataAccessException;

    // Userテーブルのデータを1件取得
    public User selectOne(String userId) throws DataAccessException;

    // Userテーブルにデータを1件insert
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルを1件更新
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを1件削除
    public int deleteOne(String userId) throws DataAccessException;
}
