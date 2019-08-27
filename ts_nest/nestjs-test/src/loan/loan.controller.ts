import { Controller, Get } from '@nestjs/common';
import { LoanTran } from './model/loan-tran';
import { LoanService } from './loan.service';

@Controller('loan')
export class LoanController {
  constructor(private readonly loanService: LoanService) {}

  @Get()
  async loanCalc(): Promise<LoanTran[]> {
    return this.loanService.loanCalc();
  }
}
