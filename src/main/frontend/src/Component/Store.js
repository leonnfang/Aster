import React from "react";
import Paper from "@material-ui/core/Paper";
import makeStyles from "@material-ui/core/styles/makeStyles";
import ListSubheader from "@material-ui/core/ListSubheader";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles((theme) => ({
    paper: {
        height: "100%",
        width: "100%",
        background: '#DFCD83',

    },
    wrapper: {
        height: 600,
        padding: '7% 7% 7% 7%'
    },
    root: {
        width: '90%',
        backgroundColor: theme.palette.background.paper,
        overflow: 'auto',
        height: '90%',
        margin: '1% 1% 1% 5%',

    },
    listSection: {
        backgroundColor: 'inherit',
    },
    ul: {
        backgroundColor: 'inherit',
        padding: 0,
    },
}));




export default function Store(props) {
    const classes = useStyles();
    const fs = props.florists;

    return(
        <div className={classes.wrapper}>
            <Paper elevation={3} className={classes.paper} style={{paddingTop: '2%'}}>
                <List className={classes.root} subheader={<li />}>

                    {props.florists.map((florist) => (
                            <li key={florist.id} className={classes.listSection}>
                                <ul className={classes.ul}>
                                    <ListSubheader>{florist.email}</ListSubheader>
                                    {florist.inventory.inventoryMap.map((flower) => (
                                        <ListItem key={flower.id}>
                                            <ListItemText>
                                                <Grid container spacing={2} direction="row">
                                                    <Grid item xs={3}>
                                                        {flower.name}
                                                    </Grid>
                                                    <Grid item xs={3}>
                                                        {flower.description}
                                                    </Grid>
                                                    <Grid item xs={3}>
                                                        {flower.quantity}
                                                    </Grid>
                                                    <Grid item xs={3}>
                                                        {flower.price}
                                                    </Grid>
                                                </Grid>
                                            </ListItemText>
                                        </ListItem>
                                    ))}
                                </ul>
                            </li>
                    ))}
                </List>
            </Paper>
        </div>
    )
}