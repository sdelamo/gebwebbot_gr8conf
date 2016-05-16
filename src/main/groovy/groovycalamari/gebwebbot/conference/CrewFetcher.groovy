package groovycalamari.gebwebbot.conference

import groovy.transform.CompileStatic

@CompileStatic
interface CrewFetcher {
    Set<Crew> fetchCrew()
}
