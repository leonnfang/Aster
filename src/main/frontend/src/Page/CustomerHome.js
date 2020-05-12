import React, {Component} from "react";
import {CustomerNavbar} from "../Component/CustomerNavbar";
import CustomerSideDrawer from "../Component/CustomerSideDrawer";
import BackDrop from "../Component/BackDrop";

export class CustomerHome extends Component {
    constructor(props) {
        super(props);

        this.state ={
            sideDrawerOpen: false
        }

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
        if(!localStorage.getItem('AuthorizationHeader')){
            this.props.history.push('/');
        }
        if(this.state.sideDrawerOpen){
            backDrop = <BackDrop click={this.backdropClickHandler}/>
        }
        return (
            <div style={{height: '100%'}}>
                <CustomerNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <CustomerSideDrawer show={this.state.sideDrawerOpen}/>
                {backDrop}
                <p>This is the customer page!</p>

            </div>
        )
    }
}

