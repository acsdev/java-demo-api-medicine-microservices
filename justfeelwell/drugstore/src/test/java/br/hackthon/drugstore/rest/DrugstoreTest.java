package br.hackthon.drugstore.rest;

import br.hackthon.account.commons.DataSource;
import br.hackthon.account.commons.HTTPCall;
import br.hackthon.account.commons.JsonUtil;
import br.hackthon.drugstore.DrugstoreStart;
import br.hackthon.drugstore.jooq.Tables;
import br.hackthon.drugstore.jooq.tables.pojos.Drugstore;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

public class DrugstoreTest {

    private static final String ENDPOINT  = "http://localhost:9002";

    @BeforeClass
    public static void setup() {

        DrugstoreStart.main(new String[]{});

        Spark.awaitInitialization();
    }

    @AfterClass
    public static void setdown() {

        Spark.stop();
    }

    @Before
    public void prepare() {
        // REMOVE JUST IN CASE
        DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);
        dsl.deleteFrom(Tables.DRUGSTORE_).where(Tables.DRUGSTORE_.DS_NAME.eq("TST.DRUG.STORE")).execute();
    }

    @Test
    public void registerTest() {

        // TODO FILL THE DATE OF DRUGSTORE

        Drugstore drugstore = new Drugstore();

        String asJson = JsonUtil.getAsJson(drugstore);

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/store/register"), asJson);
    }
}
