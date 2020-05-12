import React, {Component} from "react";
import {CustomerNavbar} from "../Component/CustomerNavbar";
import CustomerSideDrawer from "../Component/CustomerSideDrawer";
import BackDrop from "../Component/BackDrop";
import {makeStyles} from "@material-ui/core/styles";
import Cart from "../CustomerUtils/Cart";
import Slide from "@material-ui/core/Slide";
import HistoryC from "../CustomerUtils/HistoryC";
import Store from "../CustomerUtils/Store";
import Customer from "../CustomerUtils/Customer";

const useStyles = makeStyles((theme) => ({
    root: {
        height: 180,
    },
    wrapper: {
        width: 100 + theme.spacing(2),
    },
    paper: {
        zIndex: 1,
        position: 'relative',
        margin: theme.spacing(1),
    },
    svg: {
        width: 100,
        height: 100,
    },
    polygon: {
        fill: theme.palette.common.white,
        stroke: theme.palette.divider,
        strokeWidth: 1,
    },
}));

export class CustomerHome extends Component {
    constructor(props) {
        super(props);
        this.state ={
            sideDrawerOpen: false,
            cartOpen: false,
            historyOpen: false,
            storeOpen: false,
            userOpen: false
        }
        if(localStorage.getItem('usertype')==='florist'){
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
    cartClickHandler = () => {
        this.setState((prevState) => {
            return {cartOpen: !prevState.cartOpen}
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
        let cart;
        let history;
        let store;
        let user;
        if(!localStorage.getItem('AuthorizationHeader')){
            this.props.history.push('/');
        }
        if(this.state.sideDrawerOpen){
            backDrop = <BackDrop click={this.backdropClickHandler}/>
        }
        if(this.state.cartOpen){
            cart = <Cart/>
        }
        if(this.state.historyOpen){
            history = <HistoryC/>
        }
        if(this.state.storeOpen){
            store = <Store/>
        }
        if(this.state.userOpen){
            user = <Customer/>
        }
        return (
            <div style={{height: '100%', width: '100%'}}>
                <CustomerNavbar drawerClickHandler={this.drawerToggleClickHandler}/>
                <CustomerSideDrawer
                    show={this.state.sideDrawerOpen}
                    cartClick={this.cartClickHandler}
                    historyClick={this.historyClickHandler}
                    storeClick={this.storeClickHandler}
                    userClick={this.userClickHandler}
                />
                {backDrop}
                {store}
                {cart}
                {history}
                {user}
            </div>
        )
    }
}

