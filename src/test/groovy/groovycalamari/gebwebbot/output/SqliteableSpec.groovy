package groovycalamari.gebwebbot.output

import spock.lang.Specification

class SqliteableSpec extends Specification {

    def "for a class with String, BigDecimal, int properties check the columns names and types are generated correctly"() {
        when:
        List<String> columns = Product.columnsWithType()

        def expectedColumns = ['date_created TEXT','minimum_qty_purchase INTEGER','price REAL','name TEXT']
        then:
        columns
        columns.size() == 4
        columns[0] == expectedColumns[0]
        columns[1] == expectedColumns[1]
        columns[2] == expectedColumns[2]
        columns[3] == expectedColumns[3]

        when:
        def expectedTableName = 'product'
        def sqlStr = Product.sqlStatementToCreateTable()

        then:
        sqlStr == "CREATE TABLE ${expectedTableName} (${expectedColumns.join(',')})"

        when:
        sqlStr = Product.sqlStatementToDropTable()

        then:
        sqlStr == "DROP TABLE IF EXISTS ${expectedTableName}"
    }

    def "the class should persist calling saveAsSQLite"() {
        given:
        def path = "build/"

        def p = new Product(name: 'Supersonic',price: 119.0)

        when:
        p.saveAsSQLite('products', path)

        then:
        new File("${path}/products.sqlite").exists()

        when:
        def otherProduct = new Product(name: 'Torpedo', price: 100.0)
        Product.saveCollectionAsSQLite('multipleproducts',path,[p, otherProduct])

        then:
        new File("${path}/multipleproducts.sqlite").exists()
    }
}
