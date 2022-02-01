import { Difficulty } from "./difficulty";
import { Trailcomment } from "./trailcomment";

export class Trail {
  id : number;
  name : string;
  location : string;
  lengthMiles : number;
  pictureUrl : undefined | string;
  trailOpen : boolean;
  latitude : number;
  longitude : number;
  difficulty : Difficulty | undefined;
  comments : Trailcomment [] | undefined;

  constructor(id :number = 0, name : string = "", location : string = "", lengthMiles : number = 0,
               trailOpen : boolean = true, difficulty?: Difficulty, latitude: number = 39.6087891, longitude: number = -104.9049087,
               comments?: Trailcomment [], pictureUrl? : string){

                this.id = id;
                this.name = name;
                this.location = location;
                this.lengthMiles = lengthMiles;
                this.pictureUrl = pictureUrl;
                this.trailOpen = trailOpen;
                this.latitude = latitude;
                this.longitude = longitude;
                this.difficulty = difficulty;
                this.comments = comments;
              }
}
