package post

import (
    "log"
    "net/http"
    "database/sql"
    "strconv"
    "github.com/labstack/echo"
    "github.com/go-gorp/gorp"
    _ "github.com/go-sql-driver/mysql"
)

func GetLatest(c echo.Context) error {
    // TODO: DB接続部分は別パッケージ化したい
    db, err := sql.Open("mysql", "test:test@tcp(127.0.0.1:3307)/testdb?parseTime=true")
    if err != nil {
        log.Fatal(err)
    }
    dbmap := &gorp.DbMap{Db: db, Dialect: gorp.MySQLDialect{}}
    defer dbmap.Db.Close()

    var id int
    id, _ = strconv.Atoi(c.Param("id"))

    var posts []Post
    dbmap.Select(&posts, "SELECT name, body, created_at from posts WHERE id = ? LIMIT 10", id)
    if err != nil {
        log.Fatal(err)
    }

    return c.JSON(http.StatusOK, posts)
}
