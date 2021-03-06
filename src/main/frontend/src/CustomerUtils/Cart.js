import React from "react";
import Paper from "@material-ui/core/Paper";
import makeStyles from "@material-ui/core/styles/makeStyles";


const useStyles = makeStyles((theme) => ({
    paper: {
        height: "100%",
        width: "100%",
        background: '#4c5679',
    },
    wrapper: {
        height: 600,
        padding: '7% 7% 7% 7%'
    },
}));

export default function Cart(props) {
    const classes = useStyles();

    return(
        <div className={classes.wrapper}>
            <Paper elevation={3} className={classes.paper}>
                <h1>CART</h1>
            </Paper>
        </div>
    )
}