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
    render() {
        return (
            <div>
                <Router>
                    <div className='container'>
                        <Switch>
                            <Route path = "/" exact component={Home}/>
                            <Route path = "/customer" exact component={CustomerHome}/>
                            <Route path = "/florist" exact component={FloristHome}/>
                            <Route path = "/florist/login" component={FloristLogin}/>
                            <Route path = "/florist/register" component={FloristRegister}/>
                            <Route path = '/customer/login' component={CustomerLogin}/>
                            <Route path = '/customer/register' component={CustomerRegister}/>
                        </Switch>
                    </div>
                </Router>
            </div>
        )
    }
}


export default App;
