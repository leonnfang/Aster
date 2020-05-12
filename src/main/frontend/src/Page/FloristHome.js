import React, {Component} from "react";
import BackDrop from "../Component/BackDrop";
import {FloristNavbar} from "../Component/FloristNavbar";
import FloristSideDrawer from "../Component/FloristSideDrawer";
import Store from "../Component/Store";
import Inventory from "../FloristUtils/Inventory";
import Florist from "../FloristUtils/Florist";
import HistoryF from "../FloristUtils/HistoryF";

export class FloristHome extends Component {
    constructor(props) {
        super(props);

        this.state ={
            sideDrawerOpen: false,
            inventoryOpen: false,
            historyOpen: false,
            storeOpen: false,
            userOpen: false
        }
        if(localStorage.getItem('usertype')==='customer'){
            this.props.history.push('/')
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
    inventoryClickHandler = () => {
        this.setState((prevState) => {
            return {inventoryOpen: !prevState.inventoryOpen}
        })
    }
    historyClickHandler = () => {
        this.setState((prevState) => {
            return {historyOpen: !prevState.historyOpen}
        })
    }
    storeClickHandler = () => {
        this.setState((prevState) => {
            return {storeOpen: !prevState.storeOpen}
        })
    }
    userClickHandler = () => {
        this.setState((prevState) => {
            return {userOpen: !prevState.userOpen}
        })
    }


    render() {
        let backDrop;
        let inventory;
        let history;
        let store;
        let user;
        if(!localStorage.getItem('AuthorizationHeader')){
            this.props.history.push('/');
        }
        if(this.state.sideDrawerOpen){
            backDrop = <BackDrop click={this.backdropClickHandler}/>
        }
        if(this.state.inventoryOpen){
            inventory = <Inventory/>
        }
        if(this.state.historyOpen){
            history = <HistoryF/>
        }
        if(this.state.storeOpen){
            store = <Store/>
        }
        if(this.state.userOpen){
            user = <Florist/>
        }
        return (
            <div style={{height: '100%'}}>
                <FloristNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <FloristSideDrawer
                    show={this.state.sideDrawerOpen}
                    inventoryClick={this.inventoryClickHandler}
                    historyClick={this.historyClickHandler}
                    storeClick={this.storeClickHandler}
                    userClick={this.userClickHandler}
                />
                {backDrop}
                {store}
                {inventory}
                {history}
                {user}
            </div>
        )
    }
}
