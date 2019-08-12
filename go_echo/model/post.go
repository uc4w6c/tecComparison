package model

import "time"

type Post struct {
  Id        int64     `json:"id" db:"id,primarykey,autoincrement"`
  Name      string    `json:"name" db:"name,notnull`
  Body      string    `json:"body" db:"body,notnull`
  CreatedAt time.Time `json:"created_at" db:"created_at"`
  UpdatedAt time.Time `json:"updated_at" db:"updated_at"`
}

const DISPLAYABLE_SIZE int = 100

func GetLatest(id int) []Post {
    var posts []Post
    dbmap.Select(&posts, "SELECT name, body, created_at from posts WHERE id = ? LIMIT 10", id)
    return posts
}

func GetLatestByTopicId(id int) []Post {
    var posts []Post
    // dbmap.Select(&posts, "SELECT name, body, created_at from posts WHERE topic_id = ? LIMIT ?", id, DISPLAYABLE_SIZE)
    replMap := map[string]interface{}{ "id": id, "limit": DISPLAYABLE_SIZE}
    dbmap.Select(&posts, "SELECT name, body, created_at from posts WHERE topic_id = :id LIMIT :limit", replMap)
    return posts
}

func GetPostsByTopicId(id int, page int) []Post {
    var posts []Post
    // dbmap.SelectOne(&posts, "SELECT * from topics WHERE id = ? LIMIT ?, ?", id, (page - 1) * DISPLAYABLE_SIZE, DISPLAYABLE_SIZE)
    replMap := map[string]interface{}{ "id": id, "offset": (page - 1) * DISPLAYABLE_SIZE, "rowCount": DISPLAYABLE_SIZE }
    dbmap.Select(&posts, "SELECT * from posts WHERE topic_id = :id LIMIT :offset, :rowCount", replMap)
    return posts
}