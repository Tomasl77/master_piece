import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public name = "Tomas";
  constructor() { }

  ngOnInit() {
  }

  greetUser(){
    return "Welcome " + this.name;
  }

}
