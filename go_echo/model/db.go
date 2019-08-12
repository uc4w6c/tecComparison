package model

import (
    "database/sql"
    "github.com/go-gorp/gorp"
    _ "github.com/go-sql-driver/mysql"
)

var dbmap *gorp.DbMap

func init() {
    db, err := sql.Open("mysql", "test:test@tcp(127.0.0.1:3307)/testdb?parseTime=true")
    if err != nil {
      panic("failed to connect database")
    }
    dbmap := &gorp.DbMap{Db: db, Dialect: gorp.MySQLDialect{}}
    defer dbmap.Db.Close()
}
