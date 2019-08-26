import { RateInterface } from '../../model/loan/LoanTran';

export class Rate implements RateInterface{
    findRate() {
        return { interestRate: 0.5 }
    }
}
