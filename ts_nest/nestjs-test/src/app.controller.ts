import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    // nest.jsではthrowしても落ちない
    // throw new Error('example Error');
    return this.appService.getHello();
  }
}
