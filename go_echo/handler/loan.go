package handler
import (
    "../model"
    "../form"
    "fmt"
    "log"
    "net/http"
    "encoding/json"
    "github.com/labstack/echo"
)

func GetLoan(c echo.Context) error {
    const borrowingAmount = 35000000
    const repaymentPeriod = 35

    loanTrans := model.calcLoan(borrowingAmount, repaymentPeriod)
    if err := json.Unmarshal(bytes, &loanTrans); err != nil {
        log.Fatal(err)
    }
}
