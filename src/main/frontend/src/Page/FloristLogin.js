import React, {Component} from 'react'
import '../Styles/Login.css'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {makeStyles} from "@material-ui/core/styles";
import axios from "axios";

const classes = makeStyles((theme) => ({
    submit: {
        display: 'flex',
        margin: theme.spacing(3, 0, 2),

    },
}));

export class FloristLogin extends Component{
    constructor(props) {
        super(props);

        if(localStorage.getItem('AuthorizationHeader')){
            this.props.history.push('/florist');
        }
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }
    saveUser = (jwtToken, username) => {
        const myConfig = {
            params:{
                username: username
            },
            headers:{
                Authorization: jwtToken
            }
        }
        axios.get('http://localhost:8080/florist/getbyusername', myConfig)
            .then(response => {
                console.log(response.data)
                localStorage.setItem('currentUser', response.data)
            }).catch(error => {
                console.log(error)
            })
    }
    login = (e) => {
        e.preventDefault()
        console.log(this.state)
        axios.post('http://localhost:8080/login', this.state)
            .then(response => {
                console.log(response)
                const jwtToken = 'Bearer ' + response.data.jwt
                localStorage.setItem('AuthorizationHeader', jwtToken)
                localStorage.setItem('usertype', 'florist')
                this.saveUser(jwtToken, this.state.username)
                this.props.history.push('/florist/')
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
                <h2 className="text-center">Welcome to Aster</h2>
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
                    href= "/florist/register"
                >
                    Register
                </Button>
            </form>
        )
    }
}