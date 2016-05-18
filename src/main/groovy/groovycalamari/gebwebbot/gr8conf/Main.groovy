package groovycalamari.gebwebbot.gr8conf

import groovycalamari.gebwebbot.conference.Crew
import groovycalamari.gebwebbot.conference.CrewFetcher
import groovycalamari.gebwebbot.conference.Sponsor
import groovycalamari.gebwebbot.conference.SponsorFetcher

class Main {
    static final String CMD_CREW = 'crew'
    static final String CMD_SPONSORS = 'sponsors'

    static final String OUTPUT_SQLITE = 'sqlite'
    static final String OUTPUT_PLIST = 'plist'
    static final String OUTPUT_CSV = 'csv'

    @SuppressWarnings('JavaIoPackageAccess')
    static void main(String[] args) {

        boolean wrongArgs = (
                (args.size() < 5) ||
                !(args[0] in availableCommands()) ||
                !new File(args[1]).isDirectory() ||
                !(args[3] in availableOutputs()) ||
                !new File(args[4]).exists()
        )
        if ( wrongArgs ) {
            usage()
            return
        }
        String cmd = args[0]
        String destionationPath = args[1]
        String filename = args[2]
        String output = args[3]
        String phatonmJsExecutable = args[4]
        System.setProperty('phantomjs.binary.path', phatonmJsExecutable)

        CrewFetcher crewFetcher
        SponsorFetcher sponsorsFetcher
        Set<Crew> crew = []
        Set<Sponsor> sponsors = []

        switch (cmd) {
            case CMD_CREW:
                crewFetcher = new Gr8CrewFetcher()
                crew = crewFetcher.fetchCrew()
                break
            case CMD_SPONSORS:
                sponsorsFetcher = new Gr8SponsorsFetcher()
                sponsors = sponsorsFetcher.fetchSponsors()
                break
        }

        switch ( "${cmd}|${output}" ) {
            case "${CMD_CREW}|${OUTPUT_SQLITE}":
                Crew.saveCollectionAsSQLite(filename, destionationPath, crew)
                break

            case "${CMD_SPONSORS}|${OUTPUT_SQLITE}":
                Sponsor.saveCollectionAsSQLite(filename, destionationPath, sponsors)
                break

            case "${CMD_CREW}|${OUTPUT_PLIST}":
                Crew.saveCollectionAsPlist(filename, destionationPath, crew)
                break

            case "${CMD_SPONSORS}|${OUTPUT_PLIST}":
                Sponsor.saveCollectionAsPlist(filename, destionationPath, sponsors)
                break

            case "${CMD_CREW}|${OUTPUT_CSV}":
                Crew.saveCollectionAsCsv(filename, destionationPath, crew, ';')
                break

            case "${CMD_SPONSORS}|${OUTPUT_CSV}":
                Sponsor.saveCollectionAsCsv(filename, destionationPath, sponsors, ';')
                break
        }
    }

    static List<String> availableCommands() {
        [CMD_CREW, CMD_SPONSORS]
    }

    static List<String> availableOutputs() {
        [OUTPUT_SQLITE, OUTPUT_PLIST, OUTPUT_CSV]
    }

    @SuppressWarnings(['Println'])
    static void usage() {
        def cmds = availableCommands().join('|')
        def outputs = availableOutputs().join('|')
        println "java -jar project-all.jar $cmds /destionation/folder filename_without_suffix $outputs"
    }
}
