package br.hackthon.drugstore.order.model.entities;

import br.hackthon.account.commons.JsonUtil;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;

@Entity
public class Order implements Serializable {

    @Id
    private ObjectId id;

    private String accountName;

    private Point accountCoordinates;

    private String drugstoreName;

    private Point drugstoreCoordinates;

    public Order() {
        this.setId( new ObjectId() );
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

    public static void main(String[] args) {
        Order order = new Order();

        order.setAccountName( "asdasd " );
        order.setAccountCoordinates( new Point(
                new Position(-13.213123, 64.21312312)) );

        String s = JsonUtil.getAsJson( order );

        System.out.println(s);

        Order asObject = JsonUtil.getAsObject(s, Order.class);

        System.out.println( asObject );

    }
}