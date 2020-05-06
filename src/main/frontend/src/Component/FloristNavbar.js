import React, {Component} from "react";
import {Spring} from "react-spring/renderprops";
import DrawerToggleButton from "./DrawerToggleButton";

import './Toolbar.css'

export class FloristNavbar extends Component {
    render() {
        return (
            <Spring
                from={{ opacity: 0, marginTop: -500}}
                to={{ opacity: 1, marginTop: 0 }}
            >
                {props =>
                    <div style={props}>
                        <header className='toolbarFlorist'>
                            <nav className='toolbar__navigation'>
                                <div>
                                    <DrawerToggleButton click={this.props.drawerClickHandler}/>
                                </div>
                                <div className = 'toolbar__logo'><a href='/'>ASTER</a> </div>
                                <div className = 'spacer'></div>
                                <div className = 'toolbar__navigation-items'>
                                    <ul>
                                        <li><a href='https://github.com/leonnfang/Aster'>About</a> </li>
                                        <li><a href='/'>Home</a> </li>
                                    </ul>
                                </div>
                            </nav>
                        </header>
                    </div>}
            </Spring>
        )
    }
}