import { Component, OnInit } from '@angular/core';
import { MENU } from '../menu-elements';
	

@Component({
	selector: 'app-menu',
	templateUrl: './menu.component.html',
	styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

	localMenu = MENU;

	constructor() { }

	ngOnInit() {
	}

}
