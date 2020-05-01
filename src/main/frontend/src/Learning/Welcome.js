import React, {Component} from "react";

class Welcome extends Component {
    render() {
        const {name, major} = this.props
        //const {state1, state2} = this.state
        return (
            <h1>Welcome {name} studies {major}</h1>
        )
    }
}

export default Welcome