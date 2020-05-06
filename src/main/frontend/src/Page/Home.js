import React, {Component} from "react";
import ChooseCard from "../Component/ChooseCard";
import Grid from "@material-ui/core/Grid";

export class Home extends Component {

    render() {
        return (

            <div style={{padding: '15%', backgroundColor: '#DFCD83'}}>
                <Grid container spacing={1} justify='center'  direction='row'>
                    <Grid item md={5}>
                        <ChooseCard
                            who = 'Customer'
                            discription = 'Discription About Customer'
                            path = '/customer'
                            image = {require('../Image/customerPic.png')}
                        />
                    </Grid>
                    <Grid item md={5}>
                        <ChooseCard
                            who = 'Florist'
                            discription = 'Discription About Florist'
                            path = '/florist'
                            image = {require('../Image/floristPic.png')}
                        />
                    </Grid>
                </Grid>
            </div>
        )
    }
}