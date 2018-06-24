package br.hackthon.drugstore;

import br.hackthon.account.commons.JsonUtil;
import br.hackthon.drugstore.jooq.tables.pojos.Drug;
import br.hackthon.drugstore.jooq.tables.pojos.Drugstore;
import br.hackthon.drugstore.service.DrugstoreService;
import spark.Route;

import java.util.ArrayList;

public class DrugsoreRoutes {

    /**
     * Register a new drug
     */
    public static Route resgisterDrug = (request, response) -> {

        // TODO FINISH TO IMPLEMENT

        return new Drug();
    };

    /**
     * Get specificy data about a drug
     */
    public static Route recoverDrug = (request, response) -> {

        // TODO FINISH TO IMPLEMENT
        String prodcutId = request.params("prodcutId");

        return new Drug();

    };



    /**
     *  Register a new drugsstore
     */
    public static Route registerStore = (request, response) -> {

        Drugstore drugstore = JsonUtil.getAsObject(request.body(), Drugstore.class);

        // TODO FINISH TO IMPLEMENT
        return new DrugstoreService().save( drugstore );

    };


    /**
     * Associate a drug on a store
     */
    public static Route resgisterDrugOnstore = (request, response) -> {

        // TODO FINISH TO IMPLEMENT

        String storeId = request.params("storeId");
        String prodcutId = request.params("prodcutId");

        return new Drug();

    };

    /**
     * Get list of drugs of a drugstore
     */
    public static Route drugsOnDrugstore = (request, response) -> {

        // TODO FINISH TO IMPLEMENT

        String storeId = request.params("storeId");

        return new ArrayList<Drug>();

    };



}
