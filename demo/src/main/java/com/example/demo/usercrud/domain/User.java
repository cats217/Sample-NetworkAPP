package com.example.demo.usercrud.domain;

import lombok.Data;

@Data
public class User {
    private int userId; // ユーザーID
    private String password; // パスワード
    private String userName; // ユーザー名
    private int age; // 年齢
}
