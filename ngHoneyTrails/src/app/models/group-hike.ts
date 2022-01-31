import { Time } from "@angular/common";
import { GroupHikeComment } from "./group-hike-comment";
import { Trail } from "./trail";
import { User } from "./user";

export class GroupHike {
  id: number;
  eventName: string;
  meetupDate: string;
  user: User | undefined;
  trail: Trail | undefined;
  meetupTime: string;
  description: string;
  imageUrl: string;
  hidden: boolean;
  comments: GroupHikeComment [] | undefined;

  constructor(id: number = 0, eventName: string = "", meetupDate: string ="", user?: User,
    trail?: Trail, meetupTime: string = "", description: string = "", imageUrl: string = "", hidden: boolean = false, comments?: GroupHikeComment []) {
      this.id = id;
      this.eventName = eventName;
      this.meetupDate = meetupDate;
      this.user = user;
      this.trail = trail;
      this.meetupTime = meetupTime;
      this.description = description;
      this.imageUrl = imageUrl;
      this.hidden = hidden;
      this.comments = comments;
  }
}
