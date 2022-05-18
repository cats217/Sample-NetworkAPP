-- ユーザー
CREATE TABLE m_user(
  user_id INTEGER NOT NULL,
  password TEXT NOT NULL,
  user_name TEXT NOT NULL,
  age INTEGER NOT NULL,  
  PRIMARY KEY (user_id)
);

-- 削除用
DROP TABLE m_user;
