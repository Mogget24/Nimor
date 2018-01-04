import React, { Component } from 'react';
import Fetch from '../node_modules/react-fetch';

const fetch_URL = 'https://raw.githubusercontent.com/Mogget24/Nimor/html/config.json';

//import data from fetch_URL as "json";

export default class Menu extends Component {

	render(){

		return(
			// Fetch data from url and generate menu
			<Fetch url={fetch_URL}>
				<MenuComponent />
			</Fetch>
		) 
	}
}

class MenuComponent extends React.Component{
	render(){
		
		// Return the <ul> which contains the links of the menu
		return (
			<ul className="menu-wrapper list-unstyled">

				<li className="logo mm">Nimor</li>

				{this.props.menu !== undefined && this.props.menu.map((element) =>
					<li>
						<a href={element.url}>{element.text}</a>
					</li>
				)}	
			</ul>
			)
	}
}