import React, {Component} from "react";
import BackDrop from "../Component/BackDrop";
import {CustomerNavbar} from "../Component/CustomerNavbar";
import CustomerSideDrawer from "../Component/CustomerSideDrawer";
import {FloristNavbar} from "../Component/FloristNavbar";
import FloristSideDrawer from "../Component/FloristSideDrawer";

export class FloristHome extends Component {
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
                <FloristNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <FloristSideDrawer show={this.state.sideDrawerOpen}/>
                {backDrop}
                <p>This is the florist page!</p>

            </div>
        )
    }
}
