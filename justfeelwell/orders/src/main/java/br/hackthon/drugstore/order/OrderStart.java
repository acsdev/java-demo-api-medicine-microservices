package br.hackthon.drugstore.order;

import spark.Service;

public class OrderStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA
        Service http = Service.ignite();

        http.ipAddress( "localhost" );

        http.port(9003);

        // ALWAYS WORK WITH JSON
        http.after(((request, response) -> response.type("application/json")));

        http.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        http.post("/order",  OrderRoutes.newOrder);
    }

}
