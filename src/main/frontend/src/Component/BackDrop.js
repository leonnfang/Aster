import React from "react";

import '../Styles/BackDrop.css'

const BackDrop = props => (
    <div className='backdrop' onClick={props.click}/>
);

export default BackDrop;