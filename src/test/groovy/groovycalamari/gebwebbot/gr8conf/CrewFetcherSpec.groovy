package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Crew
import spock.lang.Specification

class CrewFetcherSpec extends Specification {

    def expectedCrew() {
        def crewList = [
                [
                        name: 'Søren Berg Glasius',
                        title: 'Organizer and co-Founder',
                        company: 'OCI / GR8Conf',
                        twitter: 'sbglasius',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/soeren.png'
                ],
                [
                        name: 'Brian Johnsen',
                        title: 'GR8 ApS Board Member',
                        company: 'Gennemtænkt IT',
                        twitter: 'brianjohnsendk',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/brian.png'
                ],
                [
                        name: 'Morten Kristiansen',
                        title: 'GR8 ApS Board Member',
                        company: 'Gennemtænkt IT',
                        twitter: 'mlkristiansen',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/morten.png'
                ],
                [
                        name: 'Jacob Aae Mikkelsen',
                        company: 'Lego Group',
                        twitter: 'JacobAae',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/jacob.png',
                        title: null
                ],
                [
                        name: 'Thomas Rasmussen',
                        company: 'Gennemtænkt IT',
                        twitter: 'dauer',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/thomas-r.png',
                        title: null
                ],
                [
                        name: 'Stiwi Gabriel Courage',
                        company: 'COURAGE.it',
                        twitter: 'StiwiCourage',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/stiwi.png',
                        title: null
                ],
                [
                        name: 'Niels Ull Harremoës',
                        company: 'Null Consult ApS',
                        twitter: 'Null_dk',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/niels.png',
                        title: null
                ],
                [
                        name: 'Niels Jørgen Bagger',
                        company: 'Nine A/S',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/niels-joergen.png',
                        title: null
                ],
                [
                        name: 'Anders Kristian Andersen',
                        company: 'Freelance',
                        twitter: 'anderskristian',
                        imageUrl: 'http://gr8conf.eu/images/eu/crew/anders.png',
                        title: null
                ]
        ]
        crewList.collect {
            new Crew(
                name: it.name,
                title: it.title,
                company: it.company,
                twitter: it.twitter,
                imageUrl: it.imageUrl
            )
        }
    }

    def "test fixtureData is well formed"() {
        when:
        def expectedCrew = expectedCrew()

        then:
        expectedCrew.size() == 9
        expectedCrew.each {
            assert it.name
            assert it.company
            assert it.imageUrl
        }
    }

    def "test crew are fetched correctly"() {
        given:
        def fetcher = new Gr8CrewFetcher()

        when:
        def result = fetcher.fetchCrew()
        def expectedCrew = expectedCrew()

        then:
        result
        result.size() == expectedCrew.size()
        result.each {
            assert it.imageUrl
            assert it.company
            assert it.name
        }
        for ( int i = 0; i < result.size(); i++ ) {
            assert result[i] == expectedCrew[i]
        }
    }
}
