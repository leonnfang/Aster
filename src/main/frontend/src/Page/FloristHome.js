import React, {Component} from "react";
import BackDrop from "../Component/BackDrop";
import {FloristNavbar} from "../Component/FloristNavbar";
import FloristSideDrawer from "../Component/FloristSideDrawer";

export class FloristHome extends Component {
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
                <FloristNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <FloristSideDrawer show={this.state.sideDrawerOpen}/>
                {backDrop}


            </div>
        )
    }
}
