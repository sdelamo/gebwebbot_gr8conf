package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovycalamari.gebwebbot.output.Csvable
import groovycalamari.gebwebbot.output.Plistable
import groovycalamari.gebwebbot.output.Sqliteable

@ToString
@EqualsAndHashCode
@CompileStatic
class Crew implements Sqliteable, Plistable, Csvable {
    String name
    String title
    String company
    String twitter
    String imageUrl
}
