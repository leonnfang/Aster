import React, {Component} from "react";
import ChooseCard from "../Component/ChooseCard";
import {PhotoshopPicker} from 'react-color'
import {makeStyles} from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles({
    root: {
        maxWidth: 600,
    }
});

export class Home extends Component {

    render() {
        return (
            <div style={{paddingTop: 150, paddingBottom: 150, backgroundColor: '#DFCD83'}}>
                <Grid container spacing={1} justify='center' alignItems='center' direction='row'>
                    <Grid item md={5}>
                        <ChooseCard
                            who = 'Customer'
                            discription = 'Discription About Customer'
                            path = '/customer'
                            image = {require('../Component/Image/customerPic.png')}
                        />
                    </Grid>
                    <Grid item md={5}>
                        <ChooseCard
                            who = 'Florist'
                            discription = 'Discription About Florist'
                            path = '/florist'
                            image = {require('../Component/Image/floristPic.png')}
                        />
                    </Grid>
                </Grid>
            </div>
        )
    }
}