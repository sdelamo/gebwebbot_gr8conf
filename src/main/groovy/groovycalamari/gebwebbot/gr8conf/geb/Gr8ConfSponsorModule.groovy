package groovycalamari.gebwebbot.gr8conf.geb

import geb.Module

class Gr8ConfSponsorModule extends Module {
    static content = {
        sponsorUrl { $('a', 0).getAttribute('href') }
        sponsorImageUrl { $('img', 0).getAttribute('src') }
    }
}
