 import React, {Component} from "react";
 import {CustomerNavbar} from "../Component/CustomerNavbar";
 import SideDrawer from "../Component/SideDrawer";
 import BackDrop from "../Component/BackDrop";

export class CustomerHome extends Component {
    state = {
        sideDrawerOpen: false
    }

    drawerToggleClickHandler = () => {
        this.setState((prevState) => {
            return {sideDrawerOpen: !prevState.sideDrawerOpen}
        })
    }

    backdropClickHandler = () => {
        this.setState({sideDrawerOpen: false})
    }

    render() {
        let backDrop;

        if(this.state.sideDrawerOpen){
            backDrop = <BackDrop click={this.backdropClickHandler}/>
        }
        return (
            <div style={{height: '100%'}}>
                <CustomerNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <SideDrawer show={this.state.sideDrawerOpen}/>
                {backDrop}
                <p>This is the customer page!</p>

            </div>
        )
    }
}

