package br.hackthon.account;

import br.hackthon.account.commons.JsonUtil;
import spark.Service;
import spark.Spark;

import java.io.Serializable;


public class AccountStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA
        Service http = Service.ignite();

        http.ipAddress( "localhost" );

        http.port(9001);

        // ALWAYS WORK WITH JSON
        http.after(((request, response) -> response.type("application/json")));

        http.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        http.post("/usr/signup", AccountRoutes.singUp, acc -> JsonUtil.getAsJson((Serializable) acc));

        http.post("/usr/signin", AccountRoutes.singIn, acc -> JsonUtil.getAsJson((Serializable) acc));

    }
}
