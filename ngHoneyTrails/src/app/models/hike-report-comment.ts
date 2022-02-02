import { HikeReport } from "./hike-report";
import { User } from "./user";

export class HikeReportComment {

id: number;
commentBox: string ;
hikeReport: HikeReport ;
user: User ;
createDate: string ;
// replyTo: number | undefined;

constructor(
id: number = 0,
commentBox: string = '',
hikeReport?: HikeReport,
user?: User,
createDate: string = ''
){
this.id = id;
this.commentBox = commentBox;
this.hikeReport = hikeReport? hikeReport : new HikeReport();
this.user = user? user : new User();
this.createDate = createDate;
}
}
