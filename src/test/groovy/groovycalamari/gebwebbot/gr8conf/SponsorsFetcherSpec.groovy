package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Sponsor
import spock.lang.Specification

class SponsorsFetcherSpec extends Specification {

    def expectedSponsors() {
        def sponsorsList = [
                ['http://ociweb.com/', '/images/eu/sponsors/oci.png', 'Platinum sponsor'],
                ['http://nine.dk/', '/images/eu/sponsors/nine.png', 'Platinum sponsor'],
                ['http://tothenew.com/', '/images/eu/sponsors/tothenew.png', 'Gold sponsor'],
                ['http://exensio.de/', '/images/eu/sponsors/exensio.png', 'Silver sponsor'],
                ['https://www.codecentric.de/', '/images/eu/sponsors/codecentric.png', 'Silver sponsor'],
                ['https://www.javagruppen.dk/', '/images/eu/sponsors/javagruppen.png', 'Silver sponsor'],
                ['https://www.canoo.com/', '/images/eu/sponsors/canoo.png', 'Partners'],
                ['https://www.jfrog.com/', '/images/eu/sponsors/jfrog.png', 'Partners'],
                ['https://www.codefresh.io/', '/images/eu/sponsors/codefresh.png', 'Partners'],
                ['https://www.innoq.de/', '/images/eu/sponsors/innoq.png', 'Partners'],
                ['http://www.shoptimix.com/', '/images/eu/sponsors/shoptimix.png', 'Partners'],
                ['https://www.autentia.com/', '/images/eu/sponsors/autentia.png', 'Media sponsor'],
                ['http://gradle.org/', '/images/eu/sponsors/gradle.png', 'Organizers'],
                ['http://gennemtaenkt.dk/', '/images/eu/sponsors/gennemtaenktit.png', 'Organizers'],
                ['http://easyspeedy.dk/', '/images/eu/sponsors/easyspeedy.png', 'Hosting Sponsors'],

        ]
        sponsorsList.collect { new Sponsor(url: it[0], imageUrl: "http://gr8conf.eu${it[1]}", kind: it[2]) }
    }

    def "test fixtureData is well formed"() {
        when:
        def expectedSponsors = expectedSponsors()

        then:
        expectedSponsors.size() == 15
        expectedSponsors.each {
            assert it.imageUrl
            assert it.url
            assert it.kind
        }
    }

    def "test sponsors are fetched correctly"() {
        given:

        def fetcher = new Gr8SponsorsFetcher()

        when:
        def result = fetcher.fetchSponsors()
        def expectedSponsors = expectedSponsors()

        then:
        result
        result.size() == expectedSponsors.size()
        result.each {
            assert it.imageUrl
            assert it.url
            assert it.kind
        }
        for ( int i = 0; i < result.size(); i++ ) {
            assert result[i] == expectedSponsors[i]
        }
    }
}
