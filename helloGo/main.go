package main

import (
    "github.com/labstack/echo"
    "./handler"
    "./post"
)

func main() {
    // Echoのインスタンス作る
    e := echo.New()

    // ルーティング
    e.GET("/hello", handler.MainPage())
    e.GET("/api/hello", handler.ApiHelloGet())

    e.GET("/api/post/:id", post.GetLatest)

    // サーバー起動
    e.Start(":8080")
}
