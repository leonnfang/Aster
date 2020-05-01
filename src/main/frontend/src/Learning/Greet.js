import React from 'react'

export const Greet = ({name, major}) => {

    return (
        <div>
            <h1>Hello {name} studies {major}</h1>
            {/*{props.children}*/}
        </div>
    )
}

//export default Greet