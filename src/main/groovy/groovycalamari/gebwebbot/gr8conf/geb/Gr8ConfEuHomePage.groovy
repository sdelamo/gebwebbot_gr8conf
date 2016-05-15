package groovycalamari.gebwebbot.gr8conf.geb

import geb.Page

class Gr8ConfEuHomePage extends Page {

    static content = {
        sponsorSections(required: false) { $('div.sponsors') }
        sponsorSection(required: false)  { i -> module Gr8ConfSponsorSectionModule, $('div.sponsors', i) }
    }

    boolean hasSponsorSections() {
        !sponsorSections.empty
    }
}
