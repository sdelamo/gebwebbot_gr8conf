package groovycalamari.gebwebbot.output

import groovy.transform.CompileStatic

@CompileStatic
class Product implements Sqliteable {
    String name
    BigDecimal price
    int minimumQtyPurchase = 1
    Date dateCreated = new Date()
}
