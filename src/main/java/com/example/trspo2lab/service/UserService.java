package com.example.trspo2lab.service;

import com.example.trspo2lab.model.User;
import com.example.trspo2lab.model.Item;
import com.example.trspo2lab.model.Order;

import java.util.List;

public interface UserService {

    //функції створення клієнта, замовлення та речі
    void create(User user);

    void createOrder(Order order, int client_id);

    void createItem(Item item, int client_id, int order_id);


    //функції для зчитування наявних клієнтів, замовлень та речей
    List<Order> readAllOrders(int client_id);

    List<User> readAll();

    User read(int id);

    Order readOrders(int client_id, int order_id);

    List<Item> readAllItems(int client_id, int order_id);

    Item readItems(int client_id, int order_id, int item_id);


    //функції для оновлення даних про вже існуючі об'єкти
    boolean update(User user, int id);

    boolean updateOrder(Order order, int id, int id_order);

    boolean updateItem(Item item, int id, int id_order, int id_item);


    //функції для видалення об'єктів
    boolean delete(int id);

    boolean deleteOrder(int id, int id_order);

    boolean deleteItem(int id, int id_order, int id_item);
}