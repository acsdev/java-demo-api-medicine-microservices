package br.hackthon.drugstore.order.model.entities;

import br.hackthon.account.commons.JsonUtil;
import br.hackthon.drugstore.order.model.Connection;
import br.hackthon.drugstore.order.model.entities.nested.Item;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.WhereCriteria;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Order implements Serializable {

    @Id
    private ObjectId id;

    private String accountName;

    private Point accountCoordinates;

    private String drugstoreName;

    private Point drugstoreCoordinates;

    private LocalDateTime orderDate = LocalDateTime.now();

    private List<Item> itens;

    public Order(ObjectId id) {
        this.setId( id );
    }

    public Order() {
       this( new ObjectId() );
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Point getAccountCoordinates() {
        return accountCoordinates;
    }

    public void setAccountCoordinates(Point accountCoordinates) {
        this.accountCoordinates = accountCoordinates;
    }

    public void setDrugstoreCoordinates(Point drugstoreCoordinates) {
        this.drugstoreCoordinates = drugstoreCoordinates;
    }

    public String getDrugstoreName() {
        return drugstoreName;
    }

    public void setDrugstoreName(String drugstoreName) {
        this.drugstoreName = drugstoreName;
    }

    public Point getDrugstoreCoordinates() {
        return drugstoreCoordinates;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItens() {
        if (itens == null) return null;
        return new ArrayList<>(itens); // DEFENSE PROGRAMING
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void addItem(Item item) {
        this.itens = Optional.ofNullable( this.itens ).orElse(new ArrayList<>());
        this.itens.add( item );
    }
}