package model

import (
	"time"
)

type LoanTran struct {
	Id        int64     `json:"id"`
	Mouth     time.Time `json:"mouth"`
	Total     int64     `json:"total"`
	Principal int64     `json:"principal"`
	Interest  int64     `json:"interest"`
	Balance   int64     `json:"balance"`
}

func calcLoan(borrowingAmount int, repaymentPeriod int) []Post {
	return nil
}
