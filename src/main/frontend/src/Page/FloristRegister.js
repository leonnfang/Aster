import React, {Component} from 'react'
import './style.css'

import { Button, Form, FormGroup, Label, Input } from 'reactstrap'

export class FloristRegister extends Component{
    render(){
        return(
            <Form className="login-form">
                <h1>
                    <a href='/'>ASTER</a>
                </h1>
                <h2 className="text-center">DoYouWantToJoin?</h2>
                <FormGroup>
                    <Label>Email</Label>
                    <Input type="email" placeholder="Email"/>
                </FormGroup>
                <FormGroup>
                    <Label>Password</Label>
                    <Input type="password" placeholder="Password"/>
                </FormGroup>
                <FormGroup>
                    <Label>User Name</Label>
                    <Input type="user_name" placeholder="user_name"/>
                </FormGroup>
                <FormGroup>
                    <Label>FirstName</Label>
                    <Input type="firstName" placeholder="firstName"/>
                </FormGroup>
                <FormGroup>
                    <Label>LastName</Label>
                    <Input type="lastName" placeholder="lastName"/>
                </FormGroup>
                <FormGroup>
                    <Label>Address</Label>
                    <Input type="address" placeholder="address"/>
                </FormGroup>
                <FormGroup>
                    <Button className="btn-lg btn-dark btn-block">Sign Up</Button>
                </FormGroup>
            </Form>
        )
    }
}