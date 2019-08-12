package handler

import (
    "../model"
    "net/http"
    "strconv"
    "github.com/labstack/echo"
)

func GetTopic(c echo.Context) error {
    id, _ := strconv.Atoi(c.Param("id"))
    topic := model.FindTopic(id)

    page, err := strconv.Atoi(c.QueryParam("page"))
    if err != nil {
        return c.JSON(http.StatusNotFound, "page param error")
    }
    var posts []model.Post
    if page != 0 { 
        posts = model.GetPostsByTopicId(int(topic.Id), page)
    } else {
        posts = model.GetLatestByTopicId(int(topic.Id))
    }
      
    return c.JSON(http.StatusOK, posts)
}
