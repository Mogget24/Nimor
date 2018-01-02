import React, { Component } from 'react';

export default class Menu extends Component {

	render(){

		const menu = [
			{
				text: "Homepage",
				link: "/index.html"
			},
			{
				text: "Lyrics",
				link: "/lyrics.html"
			}
		]

		const elements = menu.map((element) => 
			<li>
				<a href={element.link}>{element.text}</a>
			</li>
			)

		return(
			<ul>
				{elements}
			</ul>	
		) 
	}
}