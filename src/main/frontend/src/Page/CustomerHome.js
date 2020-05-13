import React, {Component} from "react";
import {CustomerNavbar} from "../Component/CustomerNavbar";
import CustomerSideDrawer from "../Component/CustomerSideDrawer";
import BackDrop from "../Component/BackDrop";
import Cart from "../CustomerUtils/Cart";
import HistoryC from "../CustomerUtils/HistoryC";
import Store from "../Component/Store";
import Customer from "../CustomerUtils/Customer";
import axios from 'axios';

export class CustomerHome extends Component {
    constructor(props) {
        super(props);
        this.state ={
            sideDrawerOpen: false,
            cartOpen: false,
            historyOpen: false,
            storeOpen: false,
            userOpen: false,
            customer: null
        }
        if(localStorage.getItem('email')){
            this.props.history.push('/customer/'+localStorage.getItem('email'))
        }

    }


    drawerToggleClickHandler = () => {
        this.setState((prevState) => {
            return {sideDrawerOpen: !prevState.sideDrawerOpen}
        })
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
    backdropClickHandler = () => {
        this.setState({sideDrawerOpen: false})
    }
    getCustomer = () => {
        const myConfig = {
            params:{
                customerEmail: localStorage.getItem('email')
            },
            headers:{
                Authorization: localStorage.getItem('AuthorizationHeader')
            }
        }
        axios.get('http://localhost:8080/customer/get', myConfig)
            .then(response => {
                console.log(response)
                this.setState({customer: response.data})
            })
    }


    render() {
        let backDrop;
        let cart;
        let history;
        let store;
        let user;
        if(localStorage.getItem('usertype')!='customer'){
            this.props.history.push('/')
            console.log('customertest')
        }
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
            user = <Customer {...this.state.customer}/>
        }

        return (
            <div style={{height: '100%', width: '100%'}}>
                <CustomerNavbar
                    drawerClickHandler={this.drawerToggleClickHandler}
                    updateUser={this.getCustomer}
                />
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

