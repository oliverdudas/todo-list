import {Component, OnInit} from "@angular/core";
import {User} from "../model/user";
import {UserService} from "./service/user.service";
import {Item} from "../model/item";
import {ItemService} from "./service/item.service";
import {Message} from "primeng/primeng";
import {AuthService} from "../auth/service/auth.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  model: User;
  newItem: Item;
  editItem: Item;
  deleteItem: Item;
  messages: Message[] = [];
  displayUpdateDialog: boolean = false;
  displayRemoveDialog: boolean = false;

  constructor(private router: Router,
              private userService: UserService,
              private itemService: ItemService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.model = new User();
    this.newItem = new Item();
    this.editItem = new Item();
    this.deleteItem = new Item();
    this.callUser();
  }

  saveItem() {
    this.itemService.save(this.newItem)
      .subscribe(result => {
        if (result === true) {
          console.log('Item saved!');
          console.log('Loading user items.');
          this.callUser();
          this.createMessage('success', 'Item added.', this.newItem.name);
          this.newItem.name = '';
        } else {
          this.createMessage('error', 'Saving item failed!', '');
        }
      }, error => {
        this.createMessage('error', error, '');
      });
  }

  removeItem() {
    this.itemService.remove(this.deleteItem)
      .subscribe(result => {
        if (result === true) {
          console.log('Item removed!');
          console.log('Loading user items.');
          this.callUser();
          this.createMessage('success', 'Item removed.', this.deleteItem.name);
        } else {
          this.createMessage('error', 'Item removal failed!', '');
        }
        this.displayRemoveDialog = false;
      }, error => {
        this.createMessage('error', error, '');
      });
  }

  updateItem() {
    this.itemService.update(this.editItem)
      .subscribe(result => {
        if (result === true) {
          console.log('Item updated!');
          console.log('Loading user items.');
          this.callUser();
          this.createMessage('success', 'Item updated.', this.editItem.name);
        } else {
          this.createMessage('error', 'Item update failed!', '');
        }
        this.displayUpdateDialog = false;
      }, error => {
        this.createMessage('error', error, '');
      });
  }

  showUpdateDialog(item: Item) {
    this.editItem = new Item();
    this.editItem.id = item.id;
    this.editItem.name = item.name;
    this.displayUpdateDialog = true;
  }

  showRemoveDialog(item: Item) {
    this.deleteItem = item;
    this.displayRemoveDialog = true;
  }

  private callUser() {
    this.userService.getUser()
      .subscribe(user => {
        this.model = user;
      });
  }

  logOut(): void {
    this.authService.logOut().subscribe(isLoggedIn => {
      if( isLoggedIn === false) {
        this.router.navigate(['/login']);
      }
    })
  }

  private createMessage(type: string, summary: string, detail: string) {
    this.messages = [];
    this.messages.push({severity: type, summary: summary, detail: detail});
  }

}
