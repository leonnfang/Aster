import React, {useState} from "react";
import Paper from "@material-ui/core/Paper";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import axios from 'axios';


const useStyles = makeStyles((theme) => ({
    paper: {
        height: "100%",
        width: "100%",
        background: '#794e4d',
    },
    wrapper: {
        height: 600,
        padding: '7% 7% 7% 7%'
    },
    table: {
        width: "95%",
        padding: '2.5% 2.5% 2.5% 2.5%',
    },
    root: {
        width: "100%"
    },
    container: {
        maxHeight: 450
    },
    texts: {
        display: 'flex',
        flexWrap: 'wrap',
        padding: 10
    },
}));

const columns = [
    { id: "name", label: "Flower Name", minWidth: 100 },
    { id: "description", label: "Description", minWidth: 200 },
    {
        id: "quantity",
        label: "Quantity",
        minWidth: 70,
        align: "right",
        format: value => value.toLocaleString()
    },
    {
        id: "price",
        label: "Price",
        minWidth: 70,
        align: "right",
        format: value => value.toLocaleString()
    }
];

function createData(name, description, quantity, price) {
    return { name, description, quantity, price };
}

let rows = [];


const Inventory = (props) => {
    const classes = useStyles();
    const [page] = React.useState(0);
    const [rowsPerPage] = React.useState(10);
    const [name, setname] = useState('');
    const [description, setdescription] = useState('');
    const [quantity, setquantity] = useState(0);
    const [price, setprice] = useState(0.00);
    const [flower, setflower] = useState();
    const email = localStorage.getItem('email')
    const jwtToken = localStorage.getItem('AuthorizationHeader')
    const headers = {
        'Authorization': jwtToken,
    }

    const showInventory = () => {
        console.log('show')
        setflower(null);

        const url = 'http://localhost:8080/florist/' + email + '/inventory/view'
        axios.get(url, {headers: headers})
            .then(response => {
                rows = [];
                response.data.map(flower => {
                    const newflower = createData(flower.name, flower.description, flower.quantity, flower.price)
                    setflower(newflower)
                    rows.push(flower)
                })
            })
    }
    const addFlower = (e) => {
        e.preventDefault();

        const data = {
            productName: name,
            price: price,
            description: description,
            floristEmail: email,
            quantity: quantity
        }
        const url = 'http://localhost:8080/florist/' + email + '/inventory/add'

        axios.post(url, data, {headers: headers})
            .then(response => {
                console.log(response)
                showInventory();
            })
            .catch(error => {
                console.log(error)
            })
    }
    const removeFlower = () => {
        console.log('remove')
        const url = 'http://localhost:8080/florist/' + email + '/inventory/remove'
        const myConfig = {
            params:{
                productName: name
            },
            headers:{
                Authorization: jwtToken
            }
        }
        axios.delete(url, myConfig)
            .then(response => {
                console.log(response)
                showInventory();
            })
    }


    return(
        <div className={classes.wrapper}>
            <Paper elevation={3} className={classes.paper}>
                <div className={classes.table}>
                    <Paper className={classes.root}>
                        <TableContainer className={classes.container}>
                            <Table stickyHeader aria-label="sticky table">
                                <TableHead>
                                    <TableRow>
                                        {columns.map(column => (
                                            <TableCell
                                                key={column.id}
                                                align={column.align}
                                                style={{ minWidth: column.minWidth }}
                                            >
                                                {column.label}
                                            </TableCell>
                                        ))}
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {rows
                                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                        .map(row => {
                                            return (
                                                <TableRow
                                                    hover
                                                    role="checkbox"
                                                    tabIndex={-1}
                                                    key={row.name}
                                                >
                                                    {columns.map(column => {
                                                        const value = row[column.id];
                                                        return (
                                                            <TableCell key={column.id} align={column.align}>
                                                                {column.format && typeof value === "number"
                                                                    ? column.format(value)
                                                                    : value}
                                                            </TableCell>
                                                        );
                                                    })}
                                                </TableRow>
                                            );
                                        })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Paper>
                    <Paper>
                        <form className={classes.texts} onSubmit={addFlower}>
                            <TextField
                                id="name"
                                name="name"
                                type="name"
                                label="Flower Name"
                                variant="outlined"
                                size="small"
                                margin="normal"
                                onChange={(event) => {setname(event.target.value)}}
                            />
                            <TextField
                                id="description"
                                name="description"
                                type="description"
                                label="Description"
                                variant="outlined"
                                size="small"
                                margin="normal"
                                onChange={(event) => {setdescription(event.target.value)}}
                            />
                            <TextField
                                id="quantity"
                                name="quantity"
                                type="quantity"
                                label="Quantity"
                                variant="outlined"
                                size="small"
                                margin="normal"
                                onChange={(event)=>{setquantity(event.target.value)}}
                            />
                            <TextField
                                id="price"
                                name="price"
                                type="price"
                                label="Price"
                                variant="outlined"
                                size="small"
                                margin="normal"
                                onChange={(event)=>{setprice(event.target.value)}}
                            />
                            <Button type="submit" variant="contained">+</Button>
                            <Button variant="contained" onClick={removeFlower}>-</Button>
                            <Button variant="contained" onClick={showInventory}>SHOW</Button>
                        </form>
                    </Paper>
                </div>
            </Paper>
        </div>
    )
}

export default Inventory;