import { HikeReport } from "./hike-report";

export class HikePhoto {
  id: number;
  hikeReport: HikeReport ;
  imageUrl: string | undefined;
  title: string | undefined;
  description: string | undefined;

  constructor(
    id: number = 0,
    hikeReport?: HikeReport,
    imageUrl?: string,
    title?: string,
    description?: string
  ){
    this.id = id;
    this.hikeReport = hikeReport? hikeReport : new HikeReport();
    this.imageUrl = imageUrl;
    this.title = title;
    this.description = description;
  }
}
