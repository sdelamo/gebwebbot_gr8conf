package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Sponsor
import groovycalamari.gebwebbot.conference.SponsorFetcher
import groovycalamari.gebwebbot.conference.Crew
import groovycalamari.gebwebbot.conference.CrewFetcher

class Main {
    static final String CMD_CREW = 'crew'
    static final String CMD_SPONSORS = 'sponsors'

    static final String OUTPUT_SQLITE = 'sqlite'

    @SuppressWarnings('JavaIoPackageAccess')
    static void main(String[] args) {

        boolean wrongArgs = (
                (args.size() < 4) ||
                !(args[0] in availableCommands()) ||
                !new File(args[1]).isDirectory() ||
                !(args[3] in availableOutputs())
        )
        if ( wrongArgs ) {
            usage()
            return
        }
        String cmd = args[0]
        String destionationPath = args[1]
        String filename = args[2]
        String output = args[3]

        switch ( "${cmd}|${output}" ) {
            case "${CMD_CREW}|${OUTPUT_SQLITE}":
                CrewFetcher crewFetcher = new Gr8CrewFetcher()
                Set<Crew> crew = crewFetcher.fetchCrew()
                Crew.saveCollectionAsSQLite(filename, destionationPath, crew)
                break

            case "${CMD_SPONSORS}|${OUTPUT_SQLITE}":
                SponsorFetcher sponsorsFetcher = new Gr8SponsorsFetcher()
                Set<Sponsor> sponsors = sponsorsFetcher.fetchSponsors()
                Sponsor.saveCollectionAsSQLite(filename, destionationPath, sponsors)
                break
        }
    }

    static List<String> availableCommands() {
        [CMD_CREW, CMD_SPONSORS]
    }

    static List<String> availableOutputs() {
        [OUTPUT_SQLITE]
    }

    @SuppressWarnings(['Println'])
    static void usage() {
        def cmds = availableCommands().join('|')
        def outputs = availableOutputs().join('|')
        println "java -jar project-all.jar $cmds /destionation/folder filename_without_suffix $outputs"
    }
}
