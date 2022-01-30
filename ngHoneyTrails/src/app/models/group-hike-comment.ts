import { GroupHike } from "./group-hike";
import { User } from "./user";

export class GroupHikeComment {
  id: number;
  commentBox: string;
  user: User | undefined;
  createDate: string;
  groupHike: GroupHike | undefined;

  constructor(id: number = 0, commentBox: string = "", user?: User, createDate: string = "", groupHike?: GroupHike) {
    this.id = id;
    this.commentBox = commentBox;
    this.user = user;
    this.createDate = createDate;
    this.groupHike = groupHike;
  }

}
