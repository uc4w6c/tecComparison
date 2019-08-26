// import * as Express from 'express';
import { Router, Request, Response, NextFunction } from 'express';
import { LoanTran, calcLoan } from '../../model/loan/LoanTran';

const router = Router();

// 元利均等ローン計算
router.get('/', (req: Request, res: Response, next: NextFunction) => {
    const borrowingAmount = 35000000
	const repaymentPeriod = 35
    const loanTrans = calcLoan(borrowingAmount, repaymentPeriod);

    res.status(200).json(loanTrans);
});

export default router;
