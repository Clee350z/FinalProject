import { User } from "./user";

export class Trailcomment {
  id : number;
  commentBody : string;
  timePosted : Date | undefined;
  user : User;

  constructor(id: number, commentBody : string, user : User, timePosted? : Date){
    this.id = id;
    this.commentBody = commentBody;
    this.user = user;
    this.timePosted = timePosted;
  }
}
