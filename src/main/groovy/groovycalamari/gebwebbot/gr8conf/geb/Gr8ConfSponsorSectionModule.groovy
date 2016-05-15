package groovycalamari.gebwebbot.gr8conf.geb

import geb.Module

class Gr8ConfSponsorSectionModule extends Module {
    static content = {
        kind { $('h4', 0).text() }
        sponsors { $('div.sponsor') }
        sponsor { i -> module Gr8ConfSponsorModule, $('div.sponsor', i) }
    }
}
