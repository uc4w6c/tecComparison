import { Injectable } from '@nestjs/common';
import { LoanTran, fixedRepaymentAmountCalc, repaymentMouthCountCalc, buildLoanTran } from './model/loan-tran';
import { balancesMinus } from './model/balance';
import { Rate } from './interfaces/rate.repository';
import { RateImp } from './repository/rate.repository';
import * as moment from 'moment';

@Injectable()
export class LoanService {
    loanCalc(borrowingAmount: number, repaymentPeriod: number): LoanTran[] {
        let rateInterface: Rate;
        rateInterface = new RateImp();
        const interestRate = rateInterface.findRate();
        const fixedRepaymentAmount = fixedRepaymentAmountCalc(borrowingAmount, interestRate, repaymentPeriod);
    
        const repaymentMouthCount = repaymentMouthCountCalc(repaymentPeriod);
        const balanceMinus = balancesMinus(borrowingAmount);
        let balance = borrowingAmount;
        const date = new Date();
        const loanTrans: LoanTran[] = [];
        for (let i:number = 1; i < repaymentMouthCount + 1; i += 1) {
            const loanTran = buildLoanTran(i, moment(date).format('YYYY/MM'), fixedRepaymentAmount, interestRate, balance);
            loanTrans.push(loanTran);
            date.setMonth(date.getMonth() + 1);
            balance = balanceMinus(loanTran.getPrincipal());
        }
    
        return loanTrans;
    }
}
