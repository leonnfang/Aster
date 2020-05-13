import React, {Component} from "react";
import {Spring} from "react-spring/renderprops";
import DrawerToggleButton from "./DrawerToggleButton";

import '../Styles/Toolbar.css'

export class CustomerNavbar extends Component {

    handleLogout = (e) => {
        localStorage.removeItem('AuthorizationHeader')
        localStorage.removeItem('usertype')
        localStorage.removeItem('email')
    }

    render() {
        return (
            <Spring
                from={{ opacity: 0, marginTop: -500}}
                to={{ opacity: 1, marginTop: 0 }}
            >
                {props =>
                    <div style={props}>
                        <header className='toolbarCustomer'>
                            <nav className='toolbar__navigation'>
                                <div>
                                    <DrawerToggleButton
                                        click1={this.props.drawerClickHandler}
                                        click2={this.props.updateUser}
                                    />
                                </div>
                                <div className = 'toolbar__logo'><a href='/'>ASTER</a> </div>
                                <div className = 'spacer'></div>
                                <div className = 'toolbar__navigation-items'>
                                    <ul>
                                        <li><a href='https://github.com/leonnfang/Aster'>About</a> </li>
                                        <li><a href='/'>Home</a> </li>
                                        <li><a href="#" onClick={this.handleLogout}>Logout</a></li>
                                    </ul>
                                </div>
                            </nav>
                        </header>
                    </div>}
            </Spring>
        )
    }
}
