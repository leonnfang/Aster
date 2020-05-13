import React from "react";

import '../Styles/DrawerToggleButton.css'

const DrawerToggleButton = props => (
    <button className='toggle-button' onClick={() => {props.click1(); props.click2()}}>
        <div className='toggle-button__line'/>
        <div className='toggle-button__line'/>
        <div className='toggle-button__line'/>
    </button>
);

export default DrawerToggleButton;