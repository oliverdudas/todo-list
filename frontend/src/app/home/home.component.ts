import {Component, OnInit} from "@angular/core";
import {User} from "../model/user";
import {UserService} from "./service/user.service";
import {Item} from "../model/item";
import {ItemService} from "./service/item.service";
import {Message} from "primeng/primeng";

@Component({
  moduleId: module.id,
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  model: User;
  newItem: Item;
  messages: Message[] = [];

  constructor(private userService: UserService, private itemService: ItemService) {
  }

  ngOnInit(): void {
    this.model = new User();
    this.newItem = new Item();
    this.callUser();
  }

  saveItem() {
    this.itemService.save(this.newItem)
      .subscribe(result => {
        if (result === true) {
          console.log('Item saved!');
          console.log('Loading user items.');
          this.callUser();
          this.newItem.name = '';
        } else {
          console.log('Saving item failed!');
        }
      }, error => {
        this.messages.push({severity: 'error', summary: error});
      });
  }

  private callUser() {
    this.userService.getUser()
      .subscribe(user => {
        this.model = user;
      });
  }

}
