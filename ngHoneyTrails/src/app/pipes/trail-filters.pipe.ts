import { Pipe, PipeTransform } from '@angular/core';
import { Trail } from '../models/trail';

@Pipe({
  name: 'trailFilters'
})
export class TrailFiltersPipe implements PipeTransform {

  transform(trails: Trail[]): Trail[] {
    let results : Trail[] = [];
    while(results.length < 4){
    for (const trail of trails) {

      if(results.includes(trail)){
        continue;
      }
      if(trail.id === (Math.trunc(Math.random()*trails.length) + 1)){
        results.push(trail);
      }
    }

  }
  return results;

  }
}
