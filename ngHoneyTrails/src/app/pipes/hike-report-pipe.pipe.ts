import { Pipe, PipeTransform } from '@angular/core';
import { HikeReport } from '../models/hike-report';

@Pipe({
  name: 'hikeReportPipe'
})
export class HikeReportPipePipe implements PipeTransform {

  transform(hikeReports: HikeReport[], trailId : number): HikeReport [] {

    return hikeReports;
  }

}
