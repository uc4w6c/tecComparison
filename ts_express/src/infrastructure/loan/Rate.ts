import { RateInterface } from '../../model/loan/LoanTran';

export class Rate implements RateInterface{
    findRate(): number {
        return 0.5;
    }
}
