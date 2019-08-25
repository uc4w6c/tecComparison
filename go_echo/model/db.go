package model

import (
	"database/sql"
	"log"
	"os"

	"github.com/go-gorp/gorp"
	_ "github.com/go-sql-driver/mysql"
)

var dbmap *gorp.DbMap

func init() {
	db, err := sql.Open("mysql", "test:test@tcp(127.0.0.1:3307)/testdb?parseTime=true")
	if err != nil {
		panic("failed to connect database")
	}

	dbmap = &gorp.DbMap{Db: db, Dialect: gorp.MySQLDialect{}}
	// SQLのログを常に取得する
	dbmap.TraceOn("[gorp]", log.New(os.Stdout, "gorptest:", log.Lmicroseconds))
}
