/*
 * This file is generated by jOOQ.
 */
package br.hackthon.drugstore.jooq.tables;


import br.hackthon.drugstore.jooq.Indexes;
import br.hackthon.drugstore.jooq.Keys;
import br.hackthon.drugstore.jooq.tables.records.DrugstoreRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Drugstore extends TableImpl<DrugstoreRecord> {

    private static final long serialVersionUID = 1799796028;

    /**
     * The reference instance of <code>drugstore.drugstore</code>
     */
    public static final Drugstore DRUGSTORE_ = new Drugstore();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DrugstoreRecord> getRecordType() {
        return DrugstoreRecord.class;
    }

    /**
     * The column <code>drugstore.drugstore.id_store</code>.
     */
    public final TableField<DrugstoreRecord, Long> ID_STORE = createField("id_store", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>drugstore.drugstore.ds_name</code>.
     */
    public final TableField<DrugstoreRecord, String> DS_NAME = createField("ds_name", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>drugstore.drugstore.ds_address</code>.
     */
    public final TableField<DrugstoreRecord, String> DS_ADDRESS = createField("ds_address", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>drugstore.drugstore.vl_latitude</code>.
     */
    public final TableField<DrugstoreRecord, BigDecimal> VL_LATITUDE = createField("vl_latitude", org.jooq.impl.SQLDataType.DECIMAL(10, 8), this, "");

    /**
     * The column <code>drugstore.drugstore.vl_longitude</code>.
     */
    public final TableField<DrugstoreRecord, BigDecimal> VL_LONGITUDE = createField("vl_longitude", org.jooq.impl.SQLDataType.DECIMAL(11, 8), this, "");

    /**
     * Create a <code>drugstore.drugstore</code> table reference
     */
    public Drugstore() {
        this(DSL.name("drugstore"), null);
    }

    /**
     * Create an aliased <code>drugstore.drugstore</code> table reference
     */
    public Drugstore(String alias) {
        this(DSL.name(alias), DRUGSTORE_);
    }

    /**
     * Create an aliased <code>drugstore.drugstore</code> table reference
     */
    public Drugstore(Name alias) {
        this(alias, DRUGSTORE_);
    }

    private Drugstore(Name alias, Table<DrugstoreRecord> aliased) {
        this(alias, aliased, null);
    }

    private Drugstore(Name alias, Table<DrugstoreRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Drugstore(Table<O> child, ForeignKey<O, DrugstoreRecord> key) {
        super(child, key, DRUGSTORE_);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return br.hackthon.drugstore.jooq.Drugstore.DRUGSTORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DRUGSTORE_DS_NAME, Indexes.DRUGSTORE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DrugstoreRecord, Long> getIdentity() {
        return Keys.IDENTITY_DRUGSTORE_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DrugstoreRecord> getPrimaryKey() {
        return Keys.KEY_DRUGSTORE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DrugstoreRecord>> getKeys() {
        return Arrays.<UniqueKey<DrugstoreRecord>>asList(Keys.KEY_DRUGSTORE_PRIMARY, Keys.KEY_DRUGSTORE_DS_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drugstore as(String alias) {
        return new Drugstore(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drugstore as(Name alias) {
        return new Drugstore(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Drugstore rename(String name) {
        return new Drugstore(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Drugstore rename(Name name) {
        return new Drugstore(name, null);
    }
}