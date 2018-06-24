package br.hackthon.drugstore;


import spark.Service;
import spark.Spark;

public class DrugstoreStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA
        Service http = Service.ignite();

        http.ipAddress( "localhost" );

        http.port(9002);

        // ALWAYS WORK WITH JSON
        http.after(((request, response) -> response.type("application/json")));

        http.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        http.post("/drugs/register", DrugsoreRoutes.resgisterDrug);

        http.get("/drugs/:drugId"  , DrugsoreRoutes.recoverDrug);


        http.post("/store/register", DrugsoreRoutes.registerStore);

        http.post("/store/register/:storeId/drugs", DrugsoreRoutes.resgisterDrugOnstore);


        http.get("/store/:storeId/drugs",  DrugsoreRoutes.drugsOnDrugstore);
    }


}
