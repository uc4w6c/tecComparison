package form

/*
type PostForm struct {
  Id            int64     `json:"id"`
  Name          string    `json:"name"`
  Body          string    `json:"body"`
  DeletedReason string    `json:"deleted_reason"`
  DeletedAt     time.Time `json:"deleted_at"`
  CreatedAt     time.Time `json:"created_at"`
  UpdatedAt     time.Time `json:"updated_at"`
}
*/

type PostDeleteForm struct {
  DeletedReason string    `json:"deleted_reason"`
}
