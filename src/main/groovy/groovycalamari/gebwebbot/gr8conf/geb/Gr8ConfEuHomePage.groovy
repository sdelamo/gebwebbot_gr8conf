package groovycalamari.gebwebbot.gr8conf.geb

import geb.Page

class Gr8ConfEuHomePage extends Page {
    static url = '#/'

    static content = {
        sponsorSections(wait: true, required: false) { $('div.sponsors') }
        sponsorSection(wait: true, required: false)  { i -> module Gr8ConfSponsorSectionModule, $('div.sponsors', i) }
    }
}
