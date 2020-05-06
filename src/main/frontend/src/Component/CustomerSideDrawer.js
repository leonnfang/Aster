import React, {Component} from "react";

import './SideDrawer.css'
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";

const CustomerSideDrawer = props => {
    let drawerClasses = 'side-drawer'
    if (props.show) {
        drawerClasses = 'side-drawer open'
    }

    return (
        <nav className={drawerClasses}>
            <List >
                <ListItem button key='Store'>
                    <ListItemText primary='Store'/>
                </ListItem>
                <ListItem button key='Cart'>
                    <ListItemText primary='Cart'/>
                </ListItem>
                <ListItem button key='History'>
                    <ListItemText primary='History'/>
                </ListItem>
                <ListItem button key='Flowers'>
                    <ListItemText primary='Flowers'/>
                </ListItem>
                <ListItem button key='User'>
                    <ListItemText primary='User'/>
                </ListItem>
            </List>
        </nav>
    )
}

export default CustomerSideDrawer