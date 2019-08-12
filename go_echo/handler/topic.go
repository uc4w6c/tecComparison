package handler

import (
    "../model"
    "net/http"
    "strconv"
    "github.com/labstack/echo"
)

func GetTopic(c echo.Context) error {
    id, _ := strconv.Atoi(c.Param("id"))
    // page := c.QueryParam("page") 

    topic := model.FindTopic(id)
    posts := model.GetLatestByTopicId(int(topic.Id))
    return c.JSON(http.StatusOK, posts)
}
