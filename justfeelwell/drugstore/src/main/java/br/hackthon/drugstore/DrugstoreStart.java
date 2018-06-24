package br.hackthon.drugstore;


import spark.Spark;

public class DrugstoreStart {

    public static void main(String[] args) {

        //TODO USE CONSTANTS FOR CONFIGURATION DATA

        Spark.ipAddress( "localhost" );

        Spark.port(9002);

        // ALWAYS WORK WITH JSON
        Spark.after(((request, response) -> response.type("application/json")));

        Spark.exception(RuntimeException.class, (e, request, response) -> {
            response.status( 500 );
            response.body( "Unexpected error!" );
        });

        Spark.post("/drugs/register", DrugsoreRoutes.resgisterDrug);

        Spark.get("/drugs/:drugId"  , DrugsoreRoutes.recoverDrug);


        Spark.post("/store/register", DrugsoreRoutes.registerStore);

        Spark.post("/store/register/:storeId/drugs", DrugsoreRoutes.resgisterDrugOnstore);


        Spark.get("/store/:storeId/drugs",  DrugsoreRoutes.drugsOnDrugstore);
    }


}
