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

    public getId(): number {
        return this.id;
    }
    public getMouth(): string {
        return this.mouth;
    }
    public getTotal(): number {
        return this.total;
    }
    public getPrincipal(): number {
        return this.principal;
    }
    public getInterest(): number {
        return this.interest;
    }
    public getBalance(): number {
        return this.balance;
    }
}

export const buildLoanTran = (id: number, month: string, total: number,
    interestRate: number, beforeBalance: number) : LoanTran => {

    const interest = calcMonthInterestCalc(beforeBalance, interestRate);
    const principal = total - interest;
    // 支払後残高が今回支払額より少ない場合は最終支払分と判断しまとめる
    let lastBalance = 0;
    if (beforeBalance - principal - principal < 0) {
    lastBalance = beforeBalance - principal;
    }

    return new LoanTran(id,                                         // id
        month,                                      // month
        total + lastBalance,                        // total
        principal + lastBalance,                    // principal
        interest,                                   // interest
        beforeBalance - principal - lastBalance,    // balance
    );
};

// 本来ここに記載するものではないが、簡略化のため
const roundDown = (num: number, place: number) : number => {
const shift = Math.pow(10, place);
return Math.floor(num * shift) / shift;
};

// 年利から月ごとの金利を計算
const mouthRateCalc = (interestRate: number): number => {
return roundDown(interestRate / 100 / 12, 10);
};

// 年数から支払回数を計算
const repaymentMouthCountCalc = (repaymentPeriod: number) : number => {
return repaymentPeriod * 12;
};

// 現在の残高と年利から今月の支払い利息を計算
const calcMonthInterestCalc = (balance: number, interestRate: number) : number => {
return roundDown(balance * mouthRateCalc(interestRate), 0);
};

// 毎月の一定返済金額を計算する
const fixedRepaymentAmountCalc = (balance: number, interestRate: number, repaymentPeriod:number) : number => {
const repaymentMouthCount = repaymentMouthCountCalc(repaymentPeriod);
const mouthRate = mouthRateCalc(interestRate);

const numerator = roundDown(balance * mouthRate * (Math.pow((1 + mouthRate), repaymentMouthCount)), 10);
const denominator = roundDown(Math.pow((1 + mouthRate), repaymentMouthCount) - 1, 10);
return roundDown(numerator / denominator, 0);
};
