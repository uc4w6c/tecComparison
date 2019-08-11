package post

import "time"

type Post struct {
  Id        int64     `json:"id" db:"id,primarykey,autoincrement"`
  Name      string    `json:"name" db:"name,notnull`
  Body      string    `json:"body" db:"body,notnull`
  CreatedAt time.Time `json:"created_at" db:"created_at"`
  UpdatedAt time.Time `json:"updated_at" db:"updated_at"`
}
