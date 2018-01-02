import React, { Component } from 'react';
import Menu from './Menu';

//import logo from './logo.svg';
import './App.css';


class App extends Component {

	constructor(props) {
		super(props);

		this.state = {
            isActive: false,
        };

	}

	toggleMenu(){
		const currentState = this.state.isActive;
        this.setState({ isActive: !currentState });
	}

	render() {
		return (
			<div id="main-menu" className={this.state.isActive ? 'active' : ''} >
				<button className="btn btn-toggle-menu" onClick={this.toggleMenu.bind(this)}>
					<i className="fa fa-bars" aria-hidden="true"></i>
				</button>
				<nav className="menu scrollable-menu">
					<Menu />
				</nav>
			</div>
		);
	}
}

export default App;
