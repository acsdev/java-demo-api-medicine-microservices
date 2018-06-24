package br.hackthon.drugstore.order.model;

import br.hackthon.drugstore.order.model.entities.Order;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to providade connection with mongodb using Morphia framework
 */
public class Connection {

    private Connection() {

    }

    private static Datastore datastore;

    public static Datastore get() {

        if (Connection.datastore == null) {

            Morphia mmorphia = new Morphia();

            mmorphia.mapPackage( Order.class.getPackage().getName() );


            ServerAddress addr = new ServerAddress("localhost", 27018);

            List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
            MongoCredential credentia = MongoCredential.createCredential(
                    "order", "orders", "order".toCharArray());
            credentialsList.add(credentia);

            MongoClient client = new MongoClient(addr, credentialsList);

            Connection.datastore = mmorphia.createDatastore(client, "orders");

        }

        return Connection.datastore;
    }
}
