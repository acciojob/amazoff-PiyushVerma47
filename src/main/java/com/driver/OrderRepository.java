package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
    HashMap<String, List<String>> partnerOrderMap = new HashMap<>();
    HashMap<String, String> orderPartnerMap = new HashMap<>();

    public void addOrder(Order order){
        String id = order.getId();
        orderMap.put(id,order);
    }

    public void addPartner(String partnerID){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerID);
        partnerMap.put(partnerID,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderID, String partnerID){
        List<String> list = new ArrayList<>();

        if(partnerOrderMap.containsKey(partnerID)){
            list = partnerOrderMap.get(partnerID);
        }
        list.add(orderID);
        partnerOrderMap.put(partnerID,list);

        orderPartnerMap.put(orderID,partnerID);
    }

    public Order getOrderById(String orderID){
        return orderMap.get(orderID);
    }

    public DeliveryPartner getPartnerById(String partnerID){
        return partnerMap.get(partnerID);
    }

    public Integer getOrderCountByPartnerId(String partnerID){
        List<String> list = partnerOrderMap.get(partnerID);
        return list.size();
    }

    public List<String> getOrdersByPartnerId(String partnerID){
        return partnerOrderMap.get(partnerID);
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();
        for(String orderID : orderMap.keySet()){
            list.add(orderID);
        }
        return list;
    }

    public Integer getCountOfUnassignedOrders(){
        Integer count = 0;
        for(String orderID : orderMap.keySet()){
            if(!orderPartnerMap.containsKey(orderID)) count++;
        }
        return count;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String t, String partnerID){
        int time = Integer.parseInt(t.substring(0,2))*60 + Integer.parseInt(t.substring(3));
        Integer count = 0;

        List<String> list = partnerOrderMap.get(partnerID);

        for(int i=0;i<list.size();i++){
            Order order = orderMap.get(list.get(i));
            if(order.getDeliveryTime()>time) count++;
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerID){
        List<String> list = partnerOrderMap.get(partnerID);
        String ans = "";
        int maxTime = -1;

        for(int i=0;i<list.size();i++){
            Order order = orderMap.get(list.get(i));
            if(order.getDeliveryTime()>maxTime){
                maxTime = order.getDeliveryTime();
                ans = order.getDeliveryTimeAsString(maxTime);
            }
        }
        return ans;
    }

    public void deletePartnerById(String partnerID){
        List<String> list = partnerOrderMap.get(partnerID);

        for(String orderID : list){
            orderPartnerMap.remove(orderID);
        }

        partnerMap.remove(partnerID);
        partnerOrderMap.remove(partnerID);

    }

    public void deleteOrderById(String orderID){
        String partnerID = orderPartnerMap.get(orderID);
        List<String> list = partnerOrderMap.get(partnerID);
        list.remove(orderID);
        partnerOrderMap.put(partnerID,list);

        orderPartnerMap.remove(orderID);
        orderMap.remove(orderID);
    }
}
