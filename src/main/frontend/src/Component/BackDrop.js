import React, {Component} from "react";

import './BackDrop.css'

const BackDrop = props => (
    <div className='backdrop' onClick={props.click}/>
);

export default BackDrop;