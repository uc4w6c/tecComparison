package loan

import (
	"math"
	"time"

	"../../infrastructure/loan"
)

type LoanTran struct {
	ID        int64  `json:"id"`
	Mouth     string `json:"mouth"`
	Total     int64  `json:"total"`
	Principal int64  `json:"principal"`
	Interest  int64  `json:"interest"`
	Balance   int64  `json:"balance"`
}

// 元利均等ローン計算
func CalcLoan(borrowingAmount int64, repaymentPeriod int64) []LoanTran {
	r := &loan.RepositoryImp{}
	interestRate, _ := r.FindRate()
	fixedRepaymentAmount := calcFixedRepaymentAmount(borrowingAmount, interestRate, repaymentPeriod)

	repaymentMouthCount := repaymentMouthCount(repaymentPeriod)
	balanceMinus := balancesMinus(borrowingAmount)
	balance := borrowingAmount
	date := time.Now().UTC()
	loanTrans := []LoanTran{}

	for i := 1; i < int(repaymentMouthCount + 1); i++ {
		loanTran := buildLoanTran(int64(i), date.Format("2006/01"), fixedRepaymentAmount, interestRate, balance)
		loanTrans = append(loanTrans, loanTran)
		date = date.AddDate(0, 1, 0)
		balance = balanceMinus(loanTran.Principal)
	}
	return loanTrans
}

// 本来ここに記載するものではないが、簡略化のため
func RoundDown(num float64, place int64) float64 {
	shift := math.Pow(10, float64(place))
	return math.Floor(num*shift) / shift
}

// 年利から月ごとの金利を計算
func mouthRate(interestRate float64) float64 {
	return RoundDown(interestRate/100/12, 10)
}

// 年数から支払回数を計算
func repaymentMouthCount(repaymentPeriod int64) int64 {
	return repaymentPeriod * 12
}

// 現在の残高と年利から今月の支払い利息を計算
func calcMonthInterest(balance int64, interestRate float64) int64 {
	return int64(RoundDown(float64(balance)*mouthRate(interestRate), 0))
}

// 毎月の一定返済金額を計算する
func calcFixedRepaymentAmount(balance int64, interestRate float64, repaymentPeriod int64) int64 {
	repaymentMouthCount := repaymentMouthCount(repaymentPeriod)
	mouthRate := mouthRate(interestRate)

	numerator := RoundDown(float64(balance)*mouthRate*(math.Pow((float64(1)+mouthRate), float64(repaymentMouthCount))), 10)
	denominator := RoundDown(math.Pow((float64(1)+mouthRate), float64(repaymentMouthCount))-float64(1), 10)
	return int64(RoundDown(numerator/denominator, 0))
}

func buildLoanTran(id int64, month string, total int64, interestRate float64, beforeBalance int64) LoanTran {
	interest := calcMonthInterest(beforeBalance, interestRate)
	principal := total - interest
	// 支払後残高が今回支払額より少ない場合は最終支払分と判断しまとめる
	lastBalance := int64(0)
	if beforeBalance-principal-principal < 0 {
		lastBalance = beforeBalance - principal
	}

	return LoanTran{
		ID:        id,
		Mouth:     month,
		Total:     total + lastBalance,
		Principal: principal + lastBalance,
		Interest:  interest,
		Balance:   beforeBalance - principal - lastBalance,
	}
}
