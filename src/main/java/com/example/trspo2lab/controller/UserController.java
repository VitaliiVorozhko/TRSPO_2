package com.example.trspo2lab.controller;

import com.example.trspo2lab.model.User;
import com.example.trspo2lab.model.Item;
import com.example.trspo2lab.model.Order;
import com.example.trspo2lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    //post

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/clients/{id}/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order, @PathVariable(name = "id") int id) {
        userService.createOrder(order,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/clients/{id}/orders/{id_order}/items")
    public ResponseEntity<?> createItem(@RequestBody Item item, @PathVariable(name = "id") int id, @PathVariable(name = "id_order") int id_order) {
        userService.createItem(item,id,id_order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    //get

    @GetMapping(value = "/clients")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        final User user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}/orders")
    public ResponseEntity<List<Order>> readOrders(@PathVariable(name = "id") int id) {

        final List<Order> orders = userService.readAllOrders(id);

        return orders != null
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/clients/{id}/orders/{id_order}")
    public ResponseEntity<Order> readOrders(@PathVariable(name = "id") int id,@PathVariable(name = "id_order") int id_order) {

        final Order order = userService.readOrders(id,id_order);

        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/clients/{id}/orders/{id_order}/items")
    public ResponseEntity<List<Item>> readItems(@PathVariable(name = "id") int id,@PathVariable(name = "id_order") int id_order) {

        final List<Item> items = userService.readAllItems(id, id_order);

        return items != null
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/clients/{id}/orders/{id_order}/items/{id_item}")
    public ResponseEntity<Item> readItems(@PathVariable(name = "id") int id,@PathVariable(name = "id_order") int id_order,@PathVariable(name = "id_item") int id_item) {

        final Item item = userService.readItems(id,id_order,id_item);

        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    //put

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        final boolean updated = userService.update(user, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/clients/{id}/orders/{id_order}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "id") int id, @RequestBody Order order,@PathVariable(name = "id_order") int id_order) {
        final boolean updated = userService.updateOrder(order,id,id_order);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/clients/{id}/orders/{id_order}/items/{id_item}")
    public ResponseEntity<?> updateItem(@PathVariable(name = "id") int id, @RequestBody Item item,@PathVariable(name = "id_order") int id_order,@PathVariable(name = "id_item") int id_item) {
        final boolean updated = userService.updateItem(item,id,id_order,id_item);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    //delete


    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}/orders/{id_order}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") int id,@PathVariable(name = "id_order") int id_order) {
        final boolean deleted = userService.deleteOrder(id,id_order);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}/orders/{id_order}/items/{id_item}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") int id,@PathVariable(name = "id_order") int id_order,@PathVariable(name = "id_item") int id_item) {
        final boolean deleted = userService.deleteItem(id,id_order,id_item);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}