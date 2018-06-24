package br.hackthon.drugstore.order.model;

public class Connection {
    public class Connection {

        private Connection() {

        }

        private static Datastore datastore;

        public static Datastore get() {

            if (Connection.datastore == null) {

                Morphia m = new Morphia();

                // m.mapPackage( Base.class.getPackage().getName() );

                Connection.datastore = m.createDatastore(new MongoClient(), "company");
            }

            return Connection.datastore;
        }
    }

}
