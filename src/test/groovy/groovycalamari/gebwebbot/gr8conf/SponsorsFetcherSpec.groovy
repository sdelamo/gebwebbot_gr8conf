package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Sponsor
import spock.lang.Specification

class SponsorsFetcherSpec extends Specification {

    def "test sponsors are fetched correctly"() {
        when:
        def sponsorsList = [
                ['http://ociweb.com/, images/eu/sponsors/oci.png', 'Platinum'],
                ['http://nine.dk/', 'images/eu/sponsors/nine.png', 'Platinum'],
                ['http://tothenew.com/', 'images/eu/sponsors/tothenew.png', 'Gold'],
                ['http://exensio.de/', 'images/eu/sponsors/exensio.png', 'Silver'],
                ['https://www.codecentric.de', 'images/eu/sponsors/codecentric.png', 'Silver'],
                ['https://www.javagruppen.dk', 'images/eu/sponsors/javagruppen.png', 'Silver'],
                ['https://www.canoo.com', 'images/eu/sponsors/canoo.png', 'Partners'],
                ['https://www.jfrog.com', 'images/eu/sponsors/jfrog.png', 'Partners'],
                ['https://www.codefresh.io', 'images/eu/sponsors/codefresh.png', 'Partners'],
                ['https://www.innoq.de', 'images/eu/sponsors/innoq.png', 'Partners'],
                ['https://www.autentia.com', 'images/eu/sponsors/autentia.png', 'Media sponsor'],
                ['http://gradle.org/', 'images/eu/sponsors/gradle.png', 'Organizers'],
                ['http://gennemtaenkt.dk/', 'images/eu/sponsors/gennemtaenktit.png', 'Organizers'],
                ['http://easyspeedy.dk/', 'images/eu/sponsors/easyspeedy.png', 'Hosting Sponsors'],
        ]
        def expectedSponsors = sponsorsList.collect { new Sponsor(url: it[0], imageUrl: it[1], kind: it[2]) }

        then:
        expectedSponsors.size() == 14

        when:
        def fetcher = new Gr8SponsorsFetcher()
        def result = fetcher.fetchSponsors()

        then:
        result
    }
}
