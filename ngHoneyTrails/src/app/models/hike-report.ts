import { Condition } from './condition';
import { Trail } from './trail';
import { User } from './user';

export class HikeReport {
  id: number;
  hikeTitle: string;
  report: string;
  dateCreated: string;
  hikedDate: string;
  rating: number;
  trails: Trail ;
  condition: Condition ;

  constructor(
    trails?: Trail,
    condition?: Condition,
    id?: number,
    hikeTitle?: string,
    report?: string,
    dateCreated?: string,
    hikedDate?: string,
    rating?: number,
  ) {
    this.id = id || 0;
    this.hikeTitle = hikeTitle || '';
    this.report = report || '';
    this.dateCreated = dateCreated || '';
    this.hikedDate = hikedDate || '';
    this.rating = rating || 0;
    this.trails = trails? trails: new Trail();
    this.condition = condition? condition : new Condition();
  }
}
