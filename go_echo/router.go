package main

import (
    "github.com/labstack/echo"
    "github.com/labstack/echo/middleware"
    "./handler"
)

func newRouter() *echo.Echo {
    e := echo.New()

    e.Use(middleware.Logger())
    e.Use(middleware.Recover())

    api := e.Group("/api")
    api.GET("/post/:id", handler.GetLatest) // GET /api/post/:id

    return e
}
