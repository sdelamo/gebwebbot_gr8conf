package groovycalamari.gebwebbot.gr8conf.geb

import geb.Page

class Gr8ConfCrewPage extends Page {

    static url = '#/crew'

    static content = {
        crewMembers(required: false) { $('div.crew') }
        crewMember(required: false)  { i -> module Gr8ConfCrewModule, $('div.crew', i) }
    }

    boolean hasCrewMembers() {
        !crewMembers.empty
    }
}
