import React from "react";

import '../Styles/SideDrawer.css'
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
                    <ListItemText primary='Store' onClick={props.storeClick}/>
                </ListItem>
                <ListItem button key='Cart' onClick={props.cartClick}>
                    <ListItemText primary='Cart'/>
                </ListItem>
                <ListItem button key='History' onClick={props.historyClick}>
                    <ListItemText primary='History'/>
                </ListItem>
                <ListItem button key='Flowers'>
                    <ListItemText primary='Flowers'/>
                </ListItem>
                <ListItem button key='User'>
                    <ListItemText primary='User' onClick={props.userClick}/>
                </ListItem>
            </List>
        </nav>
    )
}

export default CustomerSideDrawer