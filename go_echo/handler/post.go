package handler

import (
    "../model"
    "net/http"
    "strconv"
    "github.com/labstack/echo"
)

func GetLatest(c echo.Context) error {
    id, _ := strconv.Atoi(c.Param("id"))

    posts := model.GetLatest(id)
    return c.JSON(http.StatusOK, posts)
}
