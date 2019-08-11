package post

import (
    "fmt"
    "log"
    "time"
    "database/sql"
    "net/http"
    "github.com/labstack/echo"
    _ "github.com/go-sql-driver/mysql"
)

func GetLatest() echo.HandlerFunc {
    db, err := sql.Open("mysql", "test:test@tcp(127.0.0.1:3307)/testdb?parseTime=true")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    rows, err := db.Query(`SELECT name, body, created_at from posts LIMIT 10`)
    if err != nil {
        log.Fatal(err)
    }
    log.Printf("DB SUCCESS")

    for rows.Next() {
        var name string
        var body string
        var created_at *time.Time
        err = rows.Scan(&name, &body, &created_at)
        if err != nil {
          log.Fatal(err)
        }
        fmt.Println(name, body, created_at)
        log.Printf(name, body, created_at)
    }
    return func(c echo.Context) error {     
        return c.String(http.StatusOK, "Hello DB")
    }
}
