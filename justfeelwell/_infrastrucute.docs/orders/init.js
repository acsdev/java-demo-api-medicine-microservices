conn = new Mongo();

db = conn.getDB("admin");

db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [ { role: "userAdminAnyDatabase", db: "admin" }, { role: "__system", db: "admin" } ]
    }
)

db = conn.getDB("orders");

db.createUser({
    user: "order",
    pwd: "order",
    roles: [{role: "readWrite", db: "order"}, { role: "__system", db: "admin" }]
})