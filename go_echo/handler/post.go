package handler

import (
	"fmt"
	"net/http"
	"strconv"

	"../form"
	"../model"
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
		return c.JSON(http.StatusNotFound, "delete sql error")
	}
	fmt.Println(deleteCount)

	return c.JSON(http.StatusOK, map[string]string{
		"result": "OK",
	})
}

func InsertPost(c echo.Context) error {
	form := new(form.PostInsertForm)
	if err := c.Bind(form); err != nil {
		return c.JSON(http.StatusNotFound, "post param error")
	}

	post := model.Post{TopicId: form.TopicId, Name: form.Name, Body: form.Body}
	// insertedPost, err = model.InsertPost(post)
	err := model.InsertPost(post)
	if err != nil {
		return c.JSON(http.StatusNotFound, "post sql error")
	}
	return c.JSON(http.StatusOK, "Insert OK")
	// return c.JSON(http.StatusOK, insertedPost)
}
