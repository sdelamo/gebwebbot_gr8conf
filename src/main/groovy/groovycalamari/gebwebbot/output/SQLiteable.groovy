package groovycalamari.gebwebbot.output

import groovy.sql.Sql

trait Sqliteable extends Outputable {
    static final String DATE_FORMAT = 'yyyyMMdd_HHmmss'
    static final String SQLLITE_SUFFIX = '.sqlite'
    static final String SQLLITE_DRIVER_CLASSNAME = 'org.sqlite.JDBC'

    static String sqlColumnTypeForType(String type) {
        if ( type?.toLowerCase() in ['string', 'date'] ) {
            return 'TEXT'
        }
        else if ( type?.toLowerCase() in ['long', 'int', 'integer', 'boolean'] ) {
            return 'INTEGER'
        }
        else if ( type?.toLowerCase() in ['bigdecimal', 'float', 'double'] ) {
            return 'REAL'
        }
        'blob'
    }

    static String selectAllStatement() {
        "SELECT ${columns().join(', ')} FROM ${tableName()}"
    }

    static String sqlStatementToDropTable() {
        "DROP TABLE IF EXISTS ${tableName()}"
    }

    static String sqlStatementToCreateTable() {
        "CREATE TABLE ${tableName()} (${columnsWithType().join(',')})"
    }

    static String dbUrlWithName(String name) {
        "jdbc:sqlite:${name}"
    }

    static saveCollectionAsSQLite(String filename, String path, Collection<Sqliteable> objects) {
        persist(filename, path) {
            for ( obj in objects ) {
                def m = obj.propertiesToPersistMap()
                add(m)
            }
        }
    }

    void saveAsSQLite(String filename, String destinationPath) {
        persist(filename, destinationPath) {
            def m = propertiesToPersistMap()
            add(m)
        }
    }

    static void persist(String filename, String destinationPath, Closure cls) {

        def dbName = filename?.endsWith(SQLLITE_SUFFIX) ? "${filename}" : "${filename}${SQLLITE_SUFFIX}"
        def dbNameWithPath = "${destinationPath?.endsWith('/') ? destinationPath : destinationPath + '/'}${dbName}"
        def sql = Sql.newInstance(dbUrlWithName(dbNameWithPath), SQLLITE_DRIVER_CLASSNAME)

        for ( def sqlString in [sqlStatementToDropTable(), sqlStatementToCreateTable()] ) {
            sql.execute(sqlString as String)
        }

        def dataSet = sql.dataSet(this.tableName())
        cls.delegate = dataSet
        cls.call()

        sql.close()
    }
}

