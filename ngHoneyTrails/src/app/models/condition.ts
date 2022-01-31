export class Condition {
  id: number;
  name: string | undefined;
  description: string | undefined;

  constructor(id: number = 0, name?: string, description?: string) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
