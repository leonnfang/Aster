import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Home} from "./Page/Home";
import {CustomerHome} from "./Page/CustomerHome";
import {FloristHome} from "./Page/FloristHome";
import {FloristLogin} from "./Page/FloristLogin";
import {FloristRegister} from "./Page/FloristRegister";


class App extends Component {
    render() {
        return (
            <div className="App">
                <Router>
                    <div className='container'>
                        <Switch>
                            <Route path = "/" exact component={Home}/>
                            <Route path = "/customer" component={CustomerHome}/>
                            <Route path = "/florist" component={FloristHome}/>
                            <Route path = "/floristlogin" component={FloristLogin}/>
                            <Route path = "/floristregister" component={FloristRegister}/>
                        </Switch>
                    </div>
                </Router>
            </div>
        )
    }
}


export default App;
