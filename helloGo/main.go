package main

import (
    "github.com/labstack/echo"
    "./handler"
)

func main() {
    // Echoのインスタンス作る
    e := echo.New()

    // ルーティング
    e.GET("/hello", handler.MainPage())
    e.GET("/api/hello", handler.ApiHelloGet())

    // サーバー起動
    e.Start(":8080")
}
