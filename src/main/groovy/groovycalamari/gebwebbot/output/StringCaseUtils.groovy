package groovycalamari.gebwebbot.output

class StringCaseUtils {
    static String toCamelCase( String text, boolean capitalized = false ) {
        def cls = { String str ->
            str.replaceAll( '(_)([A-Za-z0-9])') { Object[] it ->
                it[2].toUpperCase()
            }
        }
        capitalized ? capitalize(cls(text)) : cls(text)
    }

    static String toSnakeCase( String text ) {
        text.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
    }
}
