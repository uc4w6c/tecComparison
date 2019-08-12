package handler

import (
    "../model"
    "../form"
    "fmt"
    "net/http"
    "strconv"
    "github.com/labstack/echo"
)

func GetLatest(c echo.Context) error {
    id, _ := strconv.Atoi(c.Param("id"))

    posts := model.GetLatest(id)
    return c.JSON(http.StatusOK, posts)
}

func DeletePost(c echo.Context) error {
    id, _ := strconv.Atoi(c.Param("id"))

    form := new(form.PostDeleteForm)
    if err := c.Bind(form); err != nil {
        return c.JSON(http.StatusNotFound, "delete param error")
    }

    deleteCount := model.DeletePost(id, form.DeletedReason)    
    if deleteCount != 1 {
        return c.JSON(http.StatusNotFound, "delete param error")
    }
    fmt.Println(deleteCount)

    return c.JSON(http.StatusOK, map[string]string{
        "result": "OK",
    })
}
