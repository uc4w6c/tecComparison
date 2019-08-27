import { Injectable } from '@nestjs/common';
import { LoanTran } from './model/loan-tran';

@Injectable()
export class LoanService {
    loanCalc(): LoanTran[] {
        
        // 一旦仮
        return null;
    }
}
