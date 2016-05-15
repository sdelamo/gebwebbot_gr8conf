package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.ConferenceFetcher
import groovycalamari.gebwebbot.conference.Sponsor
import groovycalamari.gebwebbot.geb.Fetcher
import groovycalamari.gebwebbot.gr8conf.geb.Gr8ConfEuHomePage

class Gr8SponsorsFetcher implements ConferenceFetcher, Fetcher {

    @Override
    Set<Sponsor> fetchSponsors() {
        def result = [] as Set
        browser.drive(baseUrl: Gr8EuWebsite.BASE_URL) {
            to Gr8ConfEuHomePage
            if ( hasSponsorSections() ) {
                for (int i = 0; i < sponsorSections.size(); i++ ) {
                    result +=  fetchSponsorsAtSection(sponsorSection(i))
                }
            }
        }
        result
    }

    Set<Sponsor> fetchSponsorsAtSection(def section) {
        def result = [] as Set
        for ( int i = 0; i < section.sponsors.size(); i++ ) {
            def s = section.sponsor(i)
            result << new Sponsor(url: s.sponsorUrl, imageUrl: s.sponsorImageUrl, kind: section.kind)
        }
        result
    }
}