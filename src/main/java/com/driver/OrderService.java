package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerID){
        orderRepository.addPartner(partnerID);
    }

    public void addOrderPartnerPair(String orderID, String partnerID){
        orderRepository.addOrderPartnerPair(orderID, partnerID);
    }

    public Order getOrderById(String orderID){
        return orderRepository.getOrderById(orderID);
    }

    public DeliveryPartner getPartnerById(String partnerID){
        return orderRepository.getPartnerById(partnerID);
    }

    public Integer getOrderCountByPartnerId(String partnerID){
        return orderRepository.getOrderCountByPartnerId(partnerID);
    }

    public List<String> getOrdersByPartnerId(String partnerID){
        return orderRepository.getOrdersByPartnerId(partnerID);
    }

    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerID){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerID);
    }

    public void deletePartnerById(String partnerID){
        orderRepository.deletePartnerById(partnerID);
    }

    public void deleteOrderById(String orderID){
        orderRepository.deleteOrderById(orderID);
    }
}
