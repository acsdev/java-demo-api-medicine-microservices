package br.hackthon.account;

import br.hackthon.account.commons.JsonUtil;
import spark.Spark;

import java.io.Serializable;


public class AccountStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA

        Spark.ipAddress( "localhost" );

        Spark.port(9001);

        // ALWAYS WORK WITH JSON
        Spark.after(((request, response) -> response.type("application/json")));

        Spark.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        Spark.post("/usr/signup", AccountRoutes.singUp, acc -> JsonUtil.getAsJson((Serializable) acc));

        Spark.post("/usr/signin", AccountRoutes.singIn, acc -> JsonUtil.getAsJson((Serializable) acc));

    }
}
