package br.hackthon.account.service;

import br.hackthon.account.AccountRoutes;
import br.hackthon.account.commons.GeoPointDTO;
import br.hackthon.account.commons.MapsUtil;
import br.hackthon.account.commons.Security;
import br.hackthon.account.commons.DataSource;
import br.hackthon.account.model.jooq.tables.pojos.Account;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountRoutes.class);

    public  static final br.hackthon.account.model.jooq.tables.Account TABLE = br.hackthon.account.model.jooq.tables.Account.ACCOUNT_;

    public Account findByUsername(String userName) {

        try {
            DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);

            List<Account> fetched = dsl.select().from(TABLE).where(TABLE.DS_USENAME.eq(userName))
                    .fetchInto(Account.class);

            return fetched.stream()
                   .findFirst().map( acc -> {
                        acc.setDsPassword( null ); // NEVER RETURN PASS
                        return acc;
                   }).orElse( null );

        } catch (Exception ex) {

            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }

    }

    public String findPassByUsername(String userName) {

        try {
            DSLContext dsl = DSL.using(DataSource.getConnection(), SQLDialect.MYSQL);

            List<String> fetched = dsl.select(TABLE.DS_PASSWORD).from(TABLE).where(TABLE.DS_USENAME.eq(userName))
                    .fetchInto(String.class);

            return fetched.stream().findFirst().orElse( null );

        } catch (Exception ex) {

            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }

    }

    public Account save(Account account) {
        // TODO validation process

        // DO OPERATION
        Connection connection = null;
        try {
            // TRY GET POSITION ON MAP
            if (account.getVlLatitude() == null && account.getVlLongitude() == null) {
                Optional<GeoPointDTO> coordinates = MapsUtil.getInstance().getCoordinates(account.getDsAddress());
                coordinates.ifPresent(g -> {
                    account.setVlLatitude(g.getLatigude());
                    account.setVlLongitude(g.getLongitude());
                });
            }

            account.setDsPassword( Security.encript( account.getDsPassword() ));

            connection= DataSource.getConnection();
            connection.setAutoCommit( false );

            DSLContext dsl = DSL.using(connection, SQLDialect.MYSQL);
            int store = dsl.newRecord(TABLE, account).store();

            if (store == 0) {
                throw new RuntimeException("Fail on store new customer");
            }
            connection.commit();

            Account byUsername = findByUsername(account.getDsUsename());

            return byUsername;

        } catch (Exception ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }



}
