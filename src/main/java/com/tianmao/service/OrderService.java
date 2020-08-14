package com.tianmao.service;

import com.tianmao.domain.PageNavigator;
import com.tianmao.pojo.Order;

/**
 * @author 胡建德
 */
public interface OrderService {

    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    Order get(int id);

    PageNavigator list(int start, int size, int num);

    void update(Order order);
}