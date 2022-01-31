import { User } from "./user";

export class Trailcomment {
  id : number;
  commentBody : string;
  timePosted : Date | undefined;
  user : User | undefined;

  constructor(id: number = 0, commentBody : string = "", user?: User, timePosted? : Date){
    this.id = id;
    this.commentBody = commentBody;
    this.user = user;
    this.timePosted = timePosted;
  }
}
