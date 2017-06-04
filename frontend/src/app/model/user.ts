import {Item} from "./item";
export class User {

  username: string;
  password: string;
  items: Item[];

  constructor() {
    this.items = [];
  }

}
