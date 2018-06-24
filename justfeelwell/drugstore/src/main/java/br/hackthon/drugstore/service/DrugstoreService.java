package br.hackthon.drugstore.service;

import br.hackthon.account.commons.DataSource;
import br.hackthon.account.commons.GeoPointDTO;
import br.hackthon.account.commons.MapsUtil;
import br.hackthon.drugstore.jooq.Tables;
import br.hackthon.drugstore.jooq.tables.pojos.Drugstore;
import br.hackthon.drugstore.jooq.tables.records.DrugstoreRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DrugstoreService {

    private static final Logger LOG = LoggerFactory.getLogger(DrugstoreService.class);

    public Drugstore save(Drugstore drugstore) {
        // TODO validation process

        // DO OPERATION
        Connection connection = null;
        try {
            // TRY GET POSITION ON MAP
            if (drugstore.getVlLatitude() == null && drugstore.getVlLongitude() == null) {
                Optional<GeoPointDTO> coordinates = MapsUtil.getInstance().getCoordinates(drugstore.getDsAddress());
                if (coordinates.isPresent()) {
                    drugstore.setVlLatitude(coordinates.get().getLatigude());
                    drugstore.setVlLongitude(coordinates.get().getLongitude());
                }
            }

            connection = DataSource.getConnection();
            connection.setAutoCommit(false);

            DSLContext dsl = DSL.using(connection, SQLDialect.MYSQL);
            DrugstoreRecord drugstoreRecord = dsl.newRecord(Tables.DRUGSTORE_, drugstore);

            int store = drugstoreRecord.store();

            if (store == 0) {
                throw new RuntimeException("Fail on store new customer");
            }
            connection.commit();

            return drugstoreRecord.into( Drugstore.class );

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
