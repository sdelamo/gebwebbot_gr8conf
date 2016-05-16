package groovycalamari.gebwebbot.output

import static java.lang.reflect.Modifier.isStatic

trait Outputable {

    static String tableName() {
        StringCaseUtils.toSnakeCase(this.simpleName)
    }

    Map propertiesToPersistMap() {
        def m = [:]
        for ( def metaBeanProperty in propertiesToPersist() ) {
            def mapKey = columName(metaBeanProperty.name)
            def mapValue = this.getProperty(metaBeanProperty.name)
            if ( metaBeanProperty.type.simpleName == 'Date' ) {
                mapValue = ((Date)this.getProperty(metaBeanProperty.name)).format(DATE_FORMAT)
            }
            m[mapKey] = mapValue
        }
        m
    }

    static propertiesToPersist() {
        this.metaClass.properties.findAll { it.name != 'class' && !isStatic(it.modifiers) }
    }

    static String columName(String name) {
        StringCaseUtils.toSnakeCase(name)
    }

    static List<String> columnsWithType() {
        propertiesToPersist().collect {
            "${columName(it.name)} ${sqlColumnTypeForType(it.type.simpleName)}"
        }
    }

    static List<String> columns() {
        propertiesToPersist().collect {
            "${columName(it.name)}"
        }
    }

    static String columnNameForFieldName(String fieldName) {

        for ( String columnName : columns() ) {
            if ( StringCaseUtils.toSnakeCase(fieldName) == columnName ) {
                return columnName
            }
        }
        null
    }
}
