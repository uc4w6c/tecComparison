package model

import "time"

type Topic struct {
  Id        int64     `json:"id" db:"id,primarykey,autoincrement"`
  Name      string    `json:"name" db:"name,notnull`
  CreatedAt time.Time `json:"created_at" db:"created_at"`
  UpdatedAt time.Time `json:"updated_at" db:"updated_at"`
}

func FindTopic(id int) Topic {
    var topic Topic
    dbmap.SelectOne(&topic, "SELECT * from topics WHERE id = ?", id)
    return topic
}
