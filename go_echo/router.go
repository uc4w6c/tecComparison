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
    api.GET("/topic/:id", handler.GetTopic)     // GET    /api/topic/:id
    api.GET("/post/:id", handler.GetLatest)     // GET    /api/post/:id
    api.DELETE("/post/:id", handler.DeletePost) // DELETE /api/post/:id
    api.POST("/post", handler.InsertPost)       // POST   /api/post

    api.GET("/loan", handler.GetLoan)           // GET    /api/loan

    return e
}
