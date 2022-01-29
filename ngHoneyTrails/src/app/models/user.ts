import { Address } from "./address";



export class User {
  id : number;
  username : string;
  password : string;
  role : string;
  enabled : boolean;
  profilePicture : string | undefined;
  firstName : string | undefined;
  lastName : string | undefined;
  address : Address | undefined;
  biography : string | undefined;

  constructor(
    id : number = 0,
    username : string = '',
    password : string = '',
    role : string = "user",
    enabled : boolean = true,
    profilePicture? : string,
    firstName? : string,
    lastName? : string,
    address? : Address,
    biography? : string)
    {
      this.id = id;
      this.username = username;
      this.password = password;
      this.profilePicture = profilePicture;
      this.role = role;
      this.enabled = enabled;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.biography = biography;
               }
}
