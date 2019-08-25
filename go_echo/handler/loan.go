package handler

import (
	"../model/loan"
	"net/http"

	"github.com/labstack/echo"
)

func GetLoan(c echo.Context) error {
	const borrowingAmount = int64(35000000)
	const repaymentPeriod = int64(35)

	loanTrans := loan.CalcLoan(borrowingAmount, repaymentPeriod)
	return c.JSON(http.StatusOK, loanTrans)
	/*
	jsonBytes, err := json.Marshal(loanTrans)
	w := http.ResponseWriter()
	if err != nil {
	    http.Error(w, err.Error(), http.StatusInternalServerError)
	return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(jsonBytes)
	return string(jsonBytes), err
	*/
	/*
	if err := json.Unmarshal(bytes, &loanTrans); err != nil {
		log.Fatal(err)
	}
	*/
}
