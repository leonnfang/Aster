import React, {Component} from 'react'
import '../Styles/Login.css'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {makeStyles} from "@material-ui/core/styles";

const classes = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',

    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        display: 'flex',
        margin: theme.spacing(3, 0, 2),

    },
}));

export class CustomerLogin extends Component{
    render(){
        return(
            <form className="login-form">
                <h1>
                    <a href='/'>ASTER</a>
                </h1>
                <h2 className="text-center">Welcome Customer</h2>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    autoFocus
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
                    type="submit"
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