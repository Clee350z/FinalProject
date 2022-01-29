import { Condition } from "./condition";
import { Trail } from "./trail";
import { User } from "./user";

export class HikeReport {
id: number;
hikeTitle: string | undefined;
trails: Trail | undefined;
condition: Condition | undefined;
report: string | undefined;
dateCreated: string | undefined;
hikedDate: string | undefined;
user: User | undefined;
rating: number | undefined;

constructor(
id: number = 0,
hikeTitle?: string,
trails?: Trail,
condition?: Condition,
report?: string,
dateCreated?: string ,
hikedDate?: string,
user?: User,
rating?: number
){
this.id = id;
this.hikeTitle = hikeTitle;
this.trails = trails;
this.condition = condition;
this.report = report;
this.dateCreated = dateCreated;
this.hikedDate = hikedDate;
this.user = user;
this.rating = rating;
}
}
