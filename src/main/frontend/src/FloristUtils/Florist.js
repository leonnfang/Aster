import React from "react";
import Paper from "@material-ui/core/Paper";
import makeStyles from "@material-ui/core/styles/makeStyles";
import TextField from '@material-ui/core/TextField';


const useStyles = makeStyles((theme) => ({
    paper: {
        height: "100%",
        width: "100%",
    },
    wrapper: {
        height: 600,
        padding: '7% 7% 7% 7%'
    },
}));

export default function Florist(props) {
    const classes = useStyles();

    return(
        <div className={classes.wrapper}>
            <Paper elevation={3} className={classes.paper}>
                <h1>FLORIST</h1>
                <h2>
                    <TextField
                        variant="outlined"
                        required
                        name="firstName"
                        id="firstName"
                        label="First Name"
                        value = {localStorage.getItem('firstName')}
                        autoFocus
                    />
                    <TextField
                        variant="outlined"
                        required
                        name="lastName"
                        id="lastName"
                        label="Last Name"
                        value = {localStorage.getItem('lastName')}
                        autoFocus
                    />
                </h2>
                <h3>
                    <TextField
                        variant="outlined"
                        required
                        name="username"
                        id="username"
                        label="User Name"
                        value = {localStorage.getItem('username')}
                        autoFocus
                    />
                </h3>
                <h4>
                    <TextField
                        variant="outlined"
                        required
                        name="email"
                        id="email"
                        label="email"
                        value = {localStorage.getItem('email')}
                        autoFocus
                    />
                </h4>
                <h5>
                    <TextField
                        variant="outlined"
                        required
                        name="address"
                        id="address"
                        label="address"
                        value = {localStorage.getItem('address')}
                        autoFocus
                    />
                </h5>
            </Paper>
        </div>
    )
}