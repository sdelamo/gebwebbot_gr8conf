package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic

@CompileStatic
interface ConferenceFetcher {
    Set<Sponsor> fetchSponsors()
}
