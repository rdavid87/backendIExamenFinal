print('Start creating database ##########################')
db = db.getSiblingDB('db_catalogs');
db.createUser(
    {
        user: 'root',
        pwd:  'root',
        roles: [{role: 'readWrite', db: 'db_catalogs'}],
    }
);

db = db.getSiblingDB('db_series');
db.createUser(
    {
        user: 'root',
        pwd:  'root',
        roles: [{role: 'readWrite', db: 'db_series'}],
    }
);
print('End creating database ##########################')