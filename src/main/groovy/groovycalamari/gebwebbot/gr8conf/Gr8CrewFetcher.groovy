package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Crew
import groovycalamari.gebwebbot.conference.CrewFetcher
import groovycalamari.gebwebbot.geb.Fetcher
import groovycalamari.gebwebbot.gr8conf.geb.Gr8ConfCrewPage

class Gr8CrewFetcher implements Fetcher, CrewFetcher {
    @Override
    Set<Crew> fetchCrew() {
        def result = [] as Set<Crew>
        browser.drive(baseUrl: Gr8EuWebsite.BASE_URL) {
            to Gr8ConfCrewPage
            for (int i = 0; i < crewMembers.size(); i++ ) {
                def c = crewMember(i)
                result << new Crew (
                        name: c.name,
                        imageUrl: c.imageUrl,
                        title: c.title ?: null,
                        company: c.company ?: null,
                        twitter: c.twitter ?: null
                )
            }
        }
        result
    }
}
