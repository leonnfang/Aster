import React, {Component} from 'react'
import '../Styles/Login.css'
import { Form } from 'reactstrap'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {makeStyles} from "@material-ui/core/styles";

const classes = makeStyles((theme) => ({
    submit: {
        display: 'flex',
        margin: theme.spacing(3, 0, 2),

    },
}));

export class FloristLogin extends Component{
    render(){
        return(
            <Form className="login-form">
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
                    href= "/florist/register"
                >
                    Register
                </Button>
            </Form>
        )
    }
}