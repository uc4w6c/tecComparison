package model

import (
    "time"
    "log"
)

type Post struct {
  Id            int64     `json:"id" db:"id,primarykey,autoincrement"`
  TopicId       int64     `json:"topic_id" db:"topic_id,notnull"`
  Name          string    `json:"name" db:"name,notnull`
  Body          string    `json:"body" db:"body,notnull`
  DeletedReason string    `json:"deleted_reason"`
  DeletedAt     time.Time `json:"deleted_at"`
  CreatedAt     time.Time `json:"created_at" db:"created_at"`
  UpdatedAt     time.Time `json:"updated_at" db:"updated_at"`
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

func DeletePost(id int, deleteReason string) int {
    replMap := map[string]interface{}{ "id": id, "deleteReason": deleteReason }
    result, err := dbmap.Exec("UPDATE posts SET deleted_reason = :deleteReason, deleted_at = cast(NOW() as datetime) WHERE id = :id", replMap)
    if err != nil {
        log.Printf("ERROR: ERROR")
    }
    if c, err := result.RowsAffected(); err != nil {
        return 0
    } else {
        return int(c)
    }
}

func InsertPost(post Post) error {
    replMap := map[string]interface{}{ "topicId": post.TopicId, "name": post.Name, "body": post.Body }
    _, err := dbmap.Exec("INSERT INTO posts (topic_id, name, body) values (:topicId, :name, :body)", replMap)
    return err

    // err := dbmap.Insert(post)
    // return err
}

// NGワードのチェックをしたいけど・・・
func (post Post) NgwordCheck() bool {
    return true
}

