import { Condition } from "./condition";
import { Trail } from "./trail";

export class HikeReport {
id: number;
hikeTitle: string | undefined;
trails: Trail | undefined;
condition: Condition | undefined;

}
