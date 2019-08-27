import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CatsController } from './cats/cats.controller';
import { CatsService } from './cats/cats.service';
import { LoanController } from './loan/loan.controller';
import { LoanService } from './loan/loan.service';

@Module({
  imports: [],
  controllers: [AppController, CatsController, LoanController],
  providers: [AppService, CatsService, LoanService],
})
export class AppModule {}
