package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
@CompileStatic
class Sponsor {
    String imageUrl
    String url
    String kind
}
