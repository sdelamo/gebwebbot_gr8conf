package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovycalamari.gebwebbot.output.Sqliteable

@ToString
@EqualsAndHashCode
@CompileStatic
class Sponsor implements Sqliteable {
    String imageUrl
    String url
    String kind
}
