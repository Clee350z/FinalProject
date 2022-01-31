export class Address {
id: number;
street: string | undefined;
city: string | undefined;
state: string | undefined;
zipcode: string | undefined;


constructor(
{ id = 0, street, city, state, zipcode }: { id?: number; street?: string; city?: string; state?: string; zipcode?: string; } = {}){
this.id = id;
this.street = street;
this.city = city;
this.state = state;
this.zipcode = zipcode;
}
}
