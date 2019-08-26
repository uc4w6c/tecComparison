import { Rate } from '../../infrastructure/loan/Rate';
import { balancesMinus } from './Balance';

export class LoanTran {
    private id: number;
    private mouth: string;
    private total: number;
    private principal: number;
    private interest: number;
    private balance: number;

    constructor(id: number,
        mouth: string,
        total: number,
        principal: number,
        interest: number,
        balance: number) {

        this.id = id;
        this.mouth = mouth;
        this.total = total;
        this.principal = principal;
        this.interest = interest;
        this.balance = balance;
    }
}

export const calcLoan = (borrowingAmount: number, repaymentPeriod: number): LoanTran[] => {
    let rateInterface: RateInterface;
    rateInterface = new Rate();
    const interestRate = rateInterface.findRate();

    const repaymentMouthCount = repaymentMouthCountCalc(repaymentPeriod);
	let balanceMinus = balancesMinus(borrowingAmount);
	let balance = borrowingAmount;
	let date = new Date();
    let loanTrans: LoanTran[] = [];
    for (let i:number = 1; i < repaymentMouthCount + 1; i++) {
        const loanTran = buildLoanTran(i, Moment(date).format("2006/01"), fixedRepaymentAmount, interestRate, balance);
		loanTrans = append(loanTrans, loanTran);
		date.setMonth( date.getDate() + 1);
		balance = balanceMinus(loanTran.Principal)
    }

    // 仮でnull返却
    return null
}

export interface RateInterface {
    findRate():{interestRate:number};
}

const buildLoanTran = (id: number, month: string, total: number, 
                       interestRate: number, beforeBalance: number) : LoanTran => {

        const interest = calcMonthInterestCalc(beforeBalance, interestRate);
        const principal = total - interest;
        // 支払後残高が今回支払額より少ない場合は最終支払分と判断しまとめる
        let lastBalance = 0;
        if (beforeBalance-principal-principal < 0) {
            lastBalance = beforeBalance - principal;
        }

        return new LoanTran(id,                                         // id
                            month,                                      // month
                            total + lastBalance,                        // total
                            principal + lastBalance,                    // principal
                            interest,                                   // interest
                            beforeBalance - principal - lastBalance,    // balance
        );
    
}


// 本来ここに記載するものではないが、簡略化のため
const RoundDown = (num: number, place: number) : number => {
	const shift = Math.pow(10, place);
	return Math.floor(num*shift) / shift;
}

// 年利から月ごとの金利を計算
const mouthRateCalc = (interestRate: number): number => {
    return RoundDown(interestRate/100/12, 10);
}

// 年数から支払回数を計算
const repaymentMouthCountCalc = (repaymentPeriod: number) : number => {
	return repaymentPeriod * 12;
}

// 現在の残高と年利から今月の支払い利息を計算
const calcMonthInterestCalc = (balance: number, interestRate: number) : number => {
	return RoundDown(balance*mouthRateCalc(interestRate), 0);
}