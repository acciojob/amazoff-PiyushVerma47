package com.driver;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    public HashMap<String, Order> orderHashMap = new HashMap<>();
    public HashMap<String, DeliveryPartner> deliveryPartnerHashMap = new HashMap<>();
    public HashMap<String, List<String>> partnerOrderHashMap = new HashMap<>();
    public HashMap<String, String> orderPartnerPairMap = new HashMap<>();


    public void addOrder(Order order){
        orderHashMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId, deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        List<String> orderlistOfPartner = new ArrayList<>();

        if(partnerOrderHashMap.containsKey(partnerId)){
            orderlistOfPartner = partnerOrderHashMap.get(partnerId);
        }
        orderlistOfPartner.add(orderId);
        partnerOrderHashMap.put(partnerId, orderlistOfPartner);

        //update orderPartnerPairMap
        orderPartnerPairMap.put(orderId, partnerId);

        //update no of orders by the delivery Partner
        DeliveryPartner deliveryPartner =deliveryPartnerHashMap.get(partnerId);
        deliveryPartner.setNumberOfOrders(orderlistOfPartner.size());
    }

    public Order getOrderById(String orderId){
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerHashMap.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId){
        Integer count = null;
        if(partnerOrderHashMap.containsKey(partnerId)){
            count = partnerOrderHashMap.get(partnerId).size();
        }
        return count;
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orderList = new ArrayList<>();
        if(partnerOrderHashMap.containsKey(partnerId)){
            orderList = partnerOrderHashMap.get(partnerId);
        }
        return orderList;
    }

    public List<String> getAllOrders(){
        List<String> allOrders = new ArrayList<>();
        for(String orderId : orderHashMap.keySet()){
            allOrders.add(orderId);
        }
        return allOrders;
    }

    public Integer getCountOfUnassignedOrders(){
        int count =0;
        for(Order order : orderHashMap.values()){
            String orderId = order.getId();
            if(!orderPartnerPairMap.containsKey(orderId)) count++;
        }
        return count;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String tyme, String partnerId){
        int count = 0;
        int time = Order.getDeliveryTimeAsInt(tyme);
        List<String> orderList = partnerOrderHashMap.get(partnerId);
        for(String orderId : orderList){
            if(orderHashMap.get(orderId).getDeliveryTime()>time){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int lastDeliveryTime = 0;
        List<String> ordersList = partnerOrderHashMap.get(partnerId);
        for(String orderId : ordersList){
            lastDeliveryTime = Math.max(lastDeliveryTime, orderHashMap.get(orderId).getDeliveryTime());
        }
        return Order.getDeliveryTimeAsString(lastDeliveryTime);
    }

    public void deletePartnerById(String  partnerId){
        //delete orders Assigned to that deliveryPartner from partnerOrderHashMap
        List<String> ordersList = partnerOrderHashMap.get(partnerId);
        for(String orderId : ordersList){
            orderPartnerPairMap.remove(orderId);
        }
        //delete it from partnerOrderMap
        partnerOrderHashMap.remove(partnerId);
        //delete order Partner Pair from deliveryPartner Hashmap
        deliveryPartnerHashMap.remove(partnerId);
    }

    public void deleteOrderById(String orderId){
        //delete it from ordersMap
        orderHashMap.remove(orderId) ;
        if(orderPartnerPairMap.containsKey(orderId)){
            String partnerId = orderPartnerPairMap.get(orderId);
            orderPartnerPairMap.remove(orderId);
            partnerOrderHashMap.get(partnerId).remove(orderId);
        }
    }
}







//package com.driver;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;



//@Repository
//public class OrderRepository {
//    HashMap<String, Order> orderMap = new HashMap<>();
//    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
//    HashMap<String, List<String>> partnerOrderMap = new HashMap<>();
//    HashMap<String, String> orderPartnerMap = new HashMap<>();
//
//    public void addOrder(Order order){
//        String id = order.getId();
//        orderMap.put(id,order);
//    }
//
//    public void addPartner(String partnerID){
//        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerID);
//        partnerMap.put(partnerID,deliveryPartner);
//    }
//
//    public void addOrderPartnerPair(String orderID, String partnerID){
//        List<String> list = new ArrayList<>();
//
//        if(partnerOrderMap.containsKey(partnerID)){
//            list = partnerOrderMap.get(partnerID);
//        }
//        list.add(orderID);
//        partnerOrderMap.put(partnerID,list);
//
//        orderPartnerMap.put(orderID,partnerID);
//    }
//
//    public Order getOrderById(String orderID){
//        return orderMap.get(orderID);
//    }
//
//    public DeliveryPartner getPartnerById(String partnerID){
//        return partnerMap.get(partnerID);
//    }
//
//    public Integer getOrderCountByPartnerId(String partnerID){
//        List<String> list = partnerOrderMap.get(partnerID);
//        return list.size();
//    }
//
//    public List<String> getOrdersByPartnerId(String partnerID){
//        return partnerOrderMap.get(partnerID);
//    }
//
//    public List<String> getAllOrders(){
//        List<String> list = new ArrayList<>();
//        for(String orderID : orderMap.keySet()){
//            list.add(orderID);
//        }
//        return list;
//    }
//
//    public Integer getCountOfUnassignedOrders(){
//        Integer count = 0;
//        for(String orderID : orderMap.keySet()){
//            if(!orderPartnerMap.containsKey(orderID)) count++;
//        }
//        return count;
//    }
//
//    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String t, String partnerID){
//        int time = Integer.parseInt(t.substring(0,2))*60 + Integer.parseInt(t.substring(3));
//        Integer count = 0;
//
//        List<String> list = partnerOrderMap.get(partnerID);
//
//        for(int i=0;i<list.size();i++){
//            Order order = orderMap.get(list.get(i));
//            if(order.getDeliveryTime()>time) count++;
//        }
//        return count;
//    }
//
//    public String getLastDeliveryTimeByPartnerId(String partnerID){
//        List<String> list = partnerOrderMap.get(partnerID);
//        String ans = "";
//        int maxTime = -1;
//
//        for(int i=0;i<list.size();i++){
//            Order order = orderMap.get(list.get(i));
//            if(order.getDeliveryTime()>maxTime){
//                maxTime = order.getDeliveryTime();
//                ans = order.getDeliveryTimeAsString(maxTime);
//            }
//        }
//        return ans;
//    }
//
//    public void deletePartnerById(String partnerID){
//        List<String> list = partnerOrderMap.get(partnerID);
//
//        for(String orderID : list){
//            orderPartnerMap.remove(orderID);
//        }
//
//        partnerMap.remove(partnerID);
//        partnerOrderMap.remove(partnerID);
//
//    }
//
//    public void deleteOrderById(String orderID){
//        String partnerID = orderPartnerMap.get(orderID);
//        List<String> list = partnerOrderMap.get(partnerID);
//        list.remove(orderID);
//        partnerOrderMap.put(partnerID,list);
//
//        orderPartnerMap.remove(orderID);
//        orderMap.remove(orderID);
//    }
//}
