// 現在の支払残高を取得する関数
export const balancesMinus = (amount: number): (payment :number) => number => {
	let balanceAmount = amount
	return (payment: number) : number => {
		balanceAmount -= payment
		return balanceAmount
	}
}