import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Home} from "./Page/Home";
import {CustomerHome} from "./Page/CustomerHome";
import {FloristHome} from "./Page/FloristHome";
import {FloristLogin} from "./Page/FloristLogin";
import {FloristRegister} from "./Page/FloristRegister";
import {CustomerLogin} from "./Page/CustomerLogin";
import {CustomerRegister} from "./Page/CustomerRegister";


class App extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div>
                <Router>
                    <div className='container'>
                        <Switch>
                            <Route path = "/" exact component={Home}/>
                            <Route path = "/florist/login" component={FloristLogin}/>
                            <Route path = "/florist/register" component={FloristRegister}/>
                            <Route path = '/customer/login' component={CustomerLogin}/>
                            <Route path = '/customer/register' component={CustomerRegister}/>
                            <Route path = "/customer/:customerEmail?" exact component={CustomerHome}/>
                            <Route path = "/florist/:floristEmail?" exact component={FloristHome}/>
                        </Switch>
                    </div>
                </Router>
            </div>
        )
    }
}


export default App;
