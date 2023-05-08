package com.example.trspo2lab.service;

import com.example.trspo2lab.model.User;
import com.example.trspo2lab.model.Item;
import com.example.trspo2lab.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    //збереження загального списку клієнтів, ключ-id, значення - клієнт
    private static final Map<Integer, User> CLIENT_REPOSITORY_MAP = new HashMap<>();


    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger ORDER_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger ITEM_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(User user) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        user.setId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId, user);
    }

    @Override
    public void createOrder(Order order, int client_id) {
        final int orderId =ORDER_ID_HOLDER.incrementAndGet();
        order.setId(orderId);
        Map<Integer, Order> CLIENT_ORDER_MAP = CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP();

        CLIENT_ORDER_MAP.put(orderId, order);
    }

    @Override
    public void createItem(Item item, int client_id, int order_id) {
        final int itemId =ITEM_ID_HOLDER.incrementAndGet();
        item.setId(itemId);
        Map<Integer, Item> ORDER_ITEMS_MAP = CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP().get(order_id).getORDER_ITEMS_MAP();

        ORDER_ITEMS_MAP.put(itemId, item);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public User read(int id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public List<Order> readAllOrders(int client_id) {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP().values());
    }

    @Override
    public Order readOrders(int client_id, int order_id) {
        return CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP().get(order_id);
    }

    @Override
    public List<Item> readAllItems(int client_id, int order_id) {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP().get(order_id).getORDER_ITEMS_MAP().values());
    }

    @Override
    public Item readItems(int client_id, int order_id, int item_id) {
        return CLIENT_REPOSITORY_MAP.get(client_id).getCLIENT_ORDER_MAP().get(order_id).getORDER_ITEMS_MAP().get(item_id);
    }



    @Override
    public boolean update(User user, int id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            user.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateOrder(Order order, int id, int id_order) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            order.setId(id_order);
            CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().put(id_order,order);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateItem(Item item, int id, int id_order, int id_item) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            item.setId(id_order);
            CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().get(id_order).getORDER_ITEMS_MAP().put(id_item,item);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public boolean deleteOrder(int id, int id_order) {
        return CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().remove(id) != null;
    }

    @Override
    public boolean deleteItem(int id, int id_order,int id_item) {
        return CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().get(id_order).getORDER_ITEMS_MAP().remove(id_item) != null;
    }
}
