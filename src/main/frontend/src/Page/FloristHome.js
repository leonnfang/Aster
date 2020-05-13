import React, {Component} from "react";
import BackDrop from "../Component/BackDrop";
import {FloristNavbar} from "../Component/FloristNavbar";
import FloristSideDrawer from "../Component/FloristSideDrawer";
import Store from "../Component/Store";
import Inventory from "../FloristUtils/Inventory";
import Florist from "../FloristUtils/Florist";
import HistoryF from "../FloristUtils/HistoryF";
import axios from 'axios';

export class FloristHome extends Component {
    constructor(props) {
        super(props);

        this.state ={
            sideDrawerOpen: false,
            inventoryOpen: false,
            historyOpen: false,
            storeOpen: false,
            userOpen: false,
            florist: null,
            florists: []
        }

        if(localStorage.getItem('email')){
            this.props.history.push('/florist/'+localStorage.getItem('email'))
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
    getFlorist = () => {
        const myConfig = {
            params:{
                floristEmail: localStorage.getItem('email')
            },
            headers:{
                Authorization: localStorage.getItem('AuthorizationHeader')
            }
        }
        axios.get('http://localhost:8080/florist/get', myConfig)
            .then(response => {
                console.log(response)
                this.setState({florist: response.data})
            })
    }
    getFlorists = () => {
        const headers = {
            'Authorization': localStorage.getItem('AuthorizationHeader'),
        }
        axios.get('http://localhost:8080/florist/getAll', {headers: headers})
            .then(response => {
                console.log(this.state.florists)
                this.setState({florists: response.data})
                console.log(this.state.florists)
            })
            .catch(error => {
                console.log('ERROR')
            })
    }


    render() {
        let backDrop;
        let inventory;
        let history;
        let store;
        let user;
        if(localStorage.getItem('usertype')!=='florist'){
            this.props.history.push('/')
            console.log('floristtest')
        }
        if(!localStorage.getItem('AuthorizationHeader')){
            this.props.history.push('/');
        }
        if(this.state.sideDrawerOpen){
            backDrop = <BackDrop click={this.backdropClickHandler}/>
        }
        if(this.state.inventoryOpen){
            inventory = <Inventory test={this.getFlorist}/>
        }
        if(this.state.historyOpen){
            history = <HistoryF/>
        }
        if(this.state.storeOpen){
            store = <Store florists={this.state.florists}/>
        }
        if(this.state.userOpen){
            user = <Florist {...this.state.florist}/>
        }
        return (
            <div style={{height: '100%', width: '100%'}}>
                <FloristNavbar
                    drawerClickHandler={this.drawerToggleClickHandler}
                    updateUser={this.getFlorist}
                    getflorists={this.getFlorists}
                />
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
