import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Typography from "@material-ui/core/Typography";
import {Link} from 'react-router-dom'

const useStyles = makeStyles({
    root: {
        maxWidth: 600
    }
});

export default function ChooseCard(props) {
    const classes = useStyles();


    return (
        <div style={{display: 'flex', justifyContent:'center'}}>
            <Link to={props.path} style={{textDecoration: 'none'}}>
                <Card className={classes.root}>
                    <CardActionArea>
                        <CardMedia
                            component="img"
                            height="350"
                            image= {props.image}
                            title={props.who}
                            style = {{display: 'flex', flexDirection: 'row'}}
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="h2">
                                {props.who}
                            </Typography>
                            <Typography variant="body2" color="textSecondary" component="p">
                                {props.discription}
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                </Card>
            </Link>
        </div>



    );
}
