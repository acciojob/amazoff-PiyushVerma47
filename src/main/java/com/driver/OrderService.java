//package com.driver;
//
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrderService {
//
//    OrderRepository orderRepository = new OrderRepository();
//
//    public void addOrder(Order order){
//        orderRepository.addOrder(order);
//    }
//
//    public void addPartner(String partnerId){
//        orderRepository.addPartner(partnerId);
//    }
//
//    public void addOrderPartnerPair(String orderId, String partnerId){
//        orderRepository.addOrderPartnerPair(orderId, partnerId);
//    }
//
//    public Order getOrderById(String orderId){
//        return orderRepository.getOrderById(orderId);
//    }
//
//    public DeliveryPartner getPartnerById(String partnerId){
//        return orderRepository.getPartnerById(partnerId);
//    }
//
//    public Integer getOrderCountByPartnerId(String partnerId){
//        return orderRepository.getOrderCountByPartnerId(partnerId);
//    }
//
//    public List<String> getOrdersByPartnerId(String partnerId){
//        return orderRepository.getOrdersByPartnerId(partnerId);
//    }
//
//    public List<String> getAllOrders(){
//        return orderRepository.getAllOrders();
//    }
//
//    public Integer getCountOfUnassignedOrders(){
//        return  orderRepository.getCountOfUnassignedOrders();
//    }
//
//    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
//        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
//    }
//
//    public String getLastDeliveryTimeByPartnerId(String partnerId){
//        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
//    }
//
//    public void deletePartnerById(String partnerId){
//        orderRepository.deletePartnerById(partnerId);
//    }
//
//    public void deleteOrderById(String orderId){
//        orderRepository.deleteOrderById(orderId);
//    }
//}










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

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerID){
        orderRepository.deletePartnerById(partnerID);
    }

    public void deleteOrderById(String orderID){
        orderRepository.deleteOrderById(orderID);
    }
}
