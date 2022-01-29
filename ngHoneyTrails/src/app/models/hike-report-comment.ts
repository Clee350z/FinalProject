import { HikeReport } from "./hike-report";
import { User } from "./user";

export class HikeReportComment {

id: number;
commentBox: string |undefined;
hikeReport: HikeReport | undefined;
user: User | undefined;
createDate: string | undefined;
replyTo: number | undefined;

constructor(
id: number = 0,
commentBox?: string,
hikeReport?: HikeReport,
user?: User,
createDate?: string,
replyTo?: number
){
this.id = id;
this.commentBox = commentBox;
this.hikeReport = hikeReport;
this.user = user;
this.createDate = createDate;
this.replyTo = replyTo;
}
}
