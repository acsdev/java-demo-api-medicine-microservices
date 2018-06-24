package br.hackthon.drugstore.order;

import br.hackthon.account.commons.JsonUtil;
import br.hackthon.account.commons.Security;
import br.hackthon.drugstore.order.model.OrderService;
import br.hackthon.drugstore.order.model.entities.Order;
import spark.Route;

public class OrderRoutes {


    public static Route newOrder = (request, response) -> {

        String token = request.headers(Security.HEADER_AUTH);

        boolean valid = Security.isTokeValid( token );
        if (! valid) {
            throw new IllegalAccessException("Invalid token"); //TODO CREATE A BETTER EXCEPTION
        }

        Order order = JsonUtil.getAsObject(request.body(), Order.class);

        return new OrderService().createOrder(token, order );
    };
}
