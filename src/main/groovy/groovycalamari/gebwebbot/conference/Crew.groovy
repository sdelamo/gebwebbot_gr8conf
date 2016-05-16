package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovycalamari.gebwebbot.output.Plistable
import groovycalamari.gebwebbot.output.Sqliteable

@ToString
@EqualsAndHashCode
@CompileStatic
class Crew implements Sqliteable, Plistable {
    String name
    String title
    String company
    String twitter
    String imageUrl
}
