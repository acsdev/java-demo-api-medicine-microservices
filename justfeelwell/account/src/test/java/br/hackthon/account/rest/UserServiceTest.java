package br.hackthon.account.rest;

import br.hackthon.account.AccountStart;
import br.hackthon.account.commons.JsonUtil;
import br.hackthon.account.model.jooq.Tables;
import br.hackthon.account.commons.DataSource;
import br.hackthon.account.commons.HTTPCall;
import br.hackthon.account.commons.LoginDTO;
import br.hackthon.account.model.jooq.tables.pojos.Account;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class UserServiceTest {

    private static final String ENDPOINT  = "http://localhost:9001";

    @BeforeClass
    public static void setup() {

        AccountStart.main(new String[]{});

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
        dsl.deleteFrom(Tables.ACCOUNT_).where(Tables.ACCOUNT_.NR_IDENTITY.eq(10000000099L)).execute();
    }

    @Test
    public void signUp() {

        String asJson = JsonUtil.getAsJson( UserServiceTest.getAccountForTest());

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/usr/signup"), asJson);
    }


    @Test
    public void signIn() {

        String toSignUp = JsonUtil.getAsJson( UserServiceTest.getAccountForTest() );

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/usr/signup"), toSignUp);

        String toSignIn = JsonUtil.getAsJson( new LoginDTO("tst.user", "123456") );

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/usr/signin"), toSignIn);
    }

    @Test(expected = RuntimeException.class)
    public void signInFail() {

        String toSignUp = JsonUtil.getAsJson( UserServiceTest.getAccountForTest() );

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/usr/signup"), toSignUp);

        String toSignIn = JsonUtil.getAsJson( new LoginDTO("tst.user", "1234567") );

        HTTPCall.doPostAndReturnJson(ENDPOINT.concat("/usr/signin"), toSignIn);
    }

    public static Account getAccountForTest() {
        long birth = LocalDate.of(1986, 5, 14)
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        Account customerDTO = new Account();

        customerDTO.setNrIdentity(10000000099L);
        customerDTO.setDsName("TST.User01");
        customerDTO.setDsUsename("tst.user");
        customerDTO.setDsPassword("123456");
        customerDTO.setDsEmail("tstuser@gmail.com");
        customerDTO.setDsAddress("424-338 Home St Winnipeg, MB R3G 1X4");
        customerDTO.setDtBirth(new Date(birth));

        return customerDTO;
    }
}
