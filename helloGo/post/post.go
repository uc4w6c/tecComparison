package post

import (
    "log"
    "net/http"
    "database/sql"
    "github.com/labstack/echo"
    "github.com/go-gorp/gorp"
    _ "github.com/go-sql-driver/mysql"
)

func GetLatest() echo.HandlerFunc {
    db, err := sql.Open("mysql", "test:test@tcp(127.0.0.1:3307)/testdb?parseTime=true")
    if err != nil {
        log.Fatal(err)
    }
    dbmap := &gorp.DbMap{Db: db, Dialect: gorp.MySQLDialect{}}
    defer dbmap.Db.Close()

    var posts []Post
    dbmap.Select(&posts, `SELECT name, body, created_at from posts LIMIT 10`)
    if err != nil {
        log.Fatal(err)
    }

    return func(c echo.Context) error {     
        return c.JSON(http.StatusOK, posts)
    }
}
