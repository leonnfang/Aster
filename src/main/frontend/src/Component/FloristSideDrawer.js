import React from "react";

import '../Styles/SideDrawer.css'
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";

const FloristSideDrawer = props => {
    let drawerClasses = 'side-drawer'
    if (props.show) {
        drawerClasses = 'side-drawer open'
    }

    return (
        <nav className={drawerClasses}>
            <List >
                <ListItem button key='Store' onClick={props.storeClick}>
                    <ListItemText primary='Store'/>
                </ListItem>
                <ListItem button key='Inventory' onClick={props.inventoryClick}>
                    <ListItemText primary='Inventory'/>
                </ListItem>
                <ListItem button key='History' onClick={props.historyClick}>
                    <ListItemText primary='History'/>
                </ListItem>
                <ListItem button key='Flowers'>
                    <ListItemText primary='Flowers'/>
                </ListItem>
                <ListItem button key='User' onClick={props.userClick}>
                    <ListItemText primary='User'/>
                </ListItem>
            </List>
        </nav>
    )
}

export default FloristSideDrawer