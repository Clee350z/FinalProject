import { Time } from "@angular/common";
import { GroupHikeComment } from "./group-hike-comment";
import { Trail } from "./trail";
import { User } from "./user";

export class GroupHike {
  id: number;
  eventName: string;
  meetupDate: string;
  user: User | undefined;
  users: User[] | undefined;
  trail: Trail;
  meetupTime: string;
  description: string;
  imageUrl: string;
  hidden: boolean;
  comments: GroupHikeComment [] | undefined;

  constructor(id: number = 0, eventName: string = "", meetupDate: string ="", users?: User[], trail?: Trail, user?: User,
     meetupTime: string = "", description: string = "", imageUrl: string = "", hidden: boolean = false, comments?: GroupHikeComment []) {
      this.id = id;
      this.eventName = eventName;
      this.meetupDate = meetupDate;
      this.users = users;
      this.user = user;
      this.trail = trail? trail: new Trail();
      this.meetupTime = meetupTime;
      this.description = description;
      this.imageUrl = imageUrl;
      this.hidden = hidden;
      this.comments = comments;
  }
}
