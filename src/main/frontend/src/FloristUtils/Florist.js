import React from "react";
import Paper from "@material-ui/core/Paper";
import makeStyles from "@material-ui/core/styles/makeStyles";

import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import Typography from "@material-ui/core/Typography";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Button from "@material-ui/core/Button";



const useStyles = makeStyles((theme) => ({
    paper: {
        height: "100%",
        width: "100%",
        background: '#794e4d',
    },
    root: {
        height: 600,
        padding: '7% 7% 7% 7%',
    },
    heading: {
        fontSize: theme.typography.pxToRem(20),
        fontWeight: theme.typography.fontWeightBold,
    },
    expansion: {
        width: "80%",
        margin: 'auto',
        padding: '3.5% 5% 0% 5%',
    },
    body: {
        fontSize: theme.typography.pxToRem(18),
        fontWeight: theme.typography.fontWeightRegular,
    }
}));

const Florist = (props) => {
    const classes = useStyles();

    return(
        <div className={classes.root}>
            <Paper elevation={3} className={classes.paper}>
                <div className={classes.expansion}>
                    <ExpansionPanel >
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography className={classes.heading}>Username</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <Typography className={classes.body}>
                                {props.username}
                                <Button color="primary">edit</Button>
                            </Typography>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                </div>
                <div className={classes.expansion}>
                    <ExpansionPanel >
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography className={classes.heading}>Email</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <Typography className={classes.body}>
                                {props.email}
                                <Button color="primary">edit</Button>
                            </Typography>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                </div>
                <div className={classes.expansion}>
                    <ExpansionPanel >
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography className={classes.heading}>First Name</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <Typography className={classes.body}>
                                {props.firstName}
                                <Button color="primary">edit</Button>
                            </Typography>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                </div>
                <div className={classes.expansion}>
                    <ExpansionPanel >
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography className={classes.heading}>Last Name</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <Typography className={classes.body}>
                                {props.lastName}
                                <Button color="primary">edit</Button>
                            </Typography>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                </div>
                <div className={classes.expansion}>
                    <ExpansionPanel >
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography className={classes.heading}>Address</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <Typography className={classes.body}>
                                {props.address}
                                <Button color="primary">edit</Button>
                            </Typography>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                </div>
            </Paper>
        </div>
    )
}

export default Florist;