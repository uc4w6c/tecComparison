package model

import "time"

type Post struct {
  Id        int64     `json:"id" db:"id,primarykey,autoincrement"`
  Name      string    `json:"name" db:"name,notnull`
  Body      string    `json:"body" db:"body,notnull`
  CreatedAt time.Time `json:"created_at" db:"created_at"`
  UpdatedAt time.Time `json:"updated_at" db:"updated_at"`
}

func GetLatest(id int) []Post {
    var posts []Post
    dbmap.Select(&posts, "SELECT name, body, created_at from posts WHERE id = ? LIMIT 10", id)
    return posts
}
