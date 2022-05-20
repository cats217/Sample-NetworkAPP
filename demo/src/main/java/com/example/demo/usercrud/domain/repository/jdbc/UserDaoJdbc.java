package com.example.demo.usercrud.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.usercrud.domain.object.User;
import com.example.demo.usercrud.domain.repository.interfaces.UserDaoIF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserDaoJdbc")
public class UserDaoJdbc implements UserDaoIF {

    @Autowired
    JdbcTemplate jdbc;

    // Userテーブルの全データを取得
    @Override
    public List<User> selectAll() throws DataAccessException {

        // SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM m_user");

        // SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql.toString());

        // 結果取得
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {

            User user = new User();
            user.setUserId((Integer) map.get("user_id"));
            user.setPassword((String) map.get("password"));
            user.setUserName((String) map.get("user_name"));
            user.setAge((Integer) map.get("age"));
            userList.add(user);
        }

        return userList;
    }

    // Userテーブルのデータを1件取得
    @Override
    public User selectOne(String userId) throws DataAccessException {

        // SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM m_user");
        sql.append(" WHERE");
        sql.append(" user_id = ?");

        // SQL実行
        Map<String, Object> map = jdbc.queryForMap(sql.toString(), Integer.parseInt(userId));

        // 結果取得
        User user = new User();
        user.setUserId((Integer) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUserName((String) map.get("user_name"));
        user.setAge((Integer) map.get("age"));

        return user;
    }

    // Userテーブルにデータを1件登録
    @Override
    public int insertOne(User user) throws DataAccessException {

        // SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO m_user");
        sql.append("(");
        sql.append(" user_id,");
        sql.append(" password,");
        sql.append(" user_name,");
        sql.append(" age");
        sql.append(")");
        sql.append(" VALUES(?, ?, ?, ?)");

        // SQL実行&結果取得
        return jdbc.update(sql.toString(),
                user.getUserId(), user.getPassword(), user.getUserName(), user.getAge());
    }

    // Userテーブルを1件更新
    @Override
    public int updateOne(User user) throws DataAccessException {

        // SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE m_user");
        sql.append(" SET");
        sql.append(" password = ?,");
        sql.append(" user_name = ?,");
        sql.append(" age = ?");
        sql.append(" WHERE");
        sql.append(" user_id = ?");

        // SQL実行&結果取得
        return jdbc.update(sql.toString(),
                user.getPassword(), user.getUserName(), user.getAge(), user.getUserId());
    }

    // Userテーブルを1件削除
    @Override
    public int deleteOne(String userId) throws DataAccessException {

        // SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM m_user");
        sql.append(" WHERE");
        sql.append(" user_id = ?");

        // SQL実行&結果取得
        return jdbc.update(sql.toString(), Integer.parseInt(userId));
    }
}
