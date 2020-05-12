import React, {Component} from 'react'
import '../Styles/Login.css'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {makeStyles} from "@material-ui/core/styles";
import axios from 'axios';

const classes = makeStyles((theme) => ({
    submit: {
        display: 'flex',
        margin: theme.spacing(3, 0, 2),

    },
}));

export class CustomerLogin extends Component{
    constructor(props) {
        super(props);

        //redirect to home if already logged in
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }
    login= (e) => {
        e.preventDefault()
        console.log(this.state)
        axios.post('http://localhost:8080/login', this.state)
            .then(response => {
                console.log(response)
                const jwtToken = 'Bearer ' + response.data.jwt
                localStorage.setItem('AuthorizationHeader', jwtToken)
                this.props.history.push('/customer/')
            })
            .catch(error => {
                console.log(error)
                alert(error.response.data.message)
            })
    }


    render(){
        return(
            <form className="login-form" onSubmit={this.login}>
                <h1>
                    <a href='/'>ASTER</a>
                </h1>
                <h2 className="text-center">Welcome Customer</h2>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="username"
                    label="Username"
                    name="username"
                    autoComplete="username"
                    autoFocus
                    onChange={this.changeHandler}
                />
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="current-password"
                    onChange={this.changeHandler}
                />
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    className={classes.submit}
                >
                    Sign In
                </Button>
                <Button
                    variant="contained"
                    color="secondary"
                    className={classes.submit}
                    href= "/customer/register"
                >
                    Register
                </Button>
            </form>
        )
    }
}