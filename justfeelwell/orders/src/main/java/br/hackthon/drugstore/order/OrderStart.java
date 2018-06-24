package br.hackthon.drugstore.order;

import spark.Route;
import spark.Spark;

import java.util.ArrayList;

public class OrderStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA

        Spark.ipAddress( "localhost" );

        Spark.port(9003);

        // ALWAYS WORK WITH JSON
        Spark.after(((request, response) -> response.type("application/json")));

        Spark.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });


        Spark.get("/order/",  null);
    }

}
