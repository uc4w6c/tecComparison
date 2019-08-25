package loan

// 現在の支払残高を取得する関数
func balancesMinus(amount int64) func(int64) int64 {
	balanceAmount := amount
	return func(payment int64) int64 {
		balanceAmount -= payment
		return balanceAmount
	}
}
