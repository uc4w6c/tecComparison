import { Rate } from '../interfaces/rate.repository';

export class RateImp implements Rate {
    findRate(): number {
        return 0.5;
    }
}