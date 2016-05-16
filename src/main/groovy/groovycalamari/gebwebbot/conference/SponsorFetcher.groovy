package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic

@CompileStatic
interface SponsorFetcher {
    Set<Sponsor> fetchSponsors()
}
