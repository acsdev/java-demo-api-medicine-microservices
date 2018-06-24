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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class DrugstoreTest {

    private static final String ENDPOINT  = "http://localhost:9002";

    @BeforeClass
    public static void setup() {

        DrugstoreStart.main(new String[]{});
    }


    @Before
    public void prepare() {
        // REMOVE JUST IN CASE
        DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);
        dsl.deleteFrom(Tables.DRUGSTORE_).where(Tables.DRUGSTORE_.DS_NAME.eq("TST.DRUG.STORE")).execute();
    }

    @Test
    public void registerTest() {


        Drugstore drugstore = new Drugstore();
        drugstore.setDsName("Great drug store");
        drugstore.setDsAddress("Great street, 456, Winnepeg"); // TODO USE GOOGLE API TO GET COORDINATES
        drugstore.setVlLatitude( new BigDecimal(49.88709860 ));
        drugstore.setVlLongitude( new BigDecimal(-97.16837890 ));

        String asJson = JsonUtil.getAsJson(drugstore);

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/store/register"), asJson);
    }
}
