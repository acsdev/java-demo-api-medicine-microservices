package br.hackthon.drugstore.order.model;

import br.hackthon.drugstore.order.model.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Class to provide methods work around Order entity
 *
 */
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    public Order createOrder(String token, Order order ) {
        //TODO VALIDATE TOKEN

        //TODO VALIDADE DATA OF ORDER
        try {
            Connection.get().save(order);

            return order; //FIXME find order on database
        } catch (Exception ex) {

            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }

    }
}
