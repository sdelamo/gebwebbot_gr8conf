package groovycalamari.gebwebbot.gr8conf.geb

import geb.Module

class Gr8ConfCrewModule extends Module {
    static content = {
        name { $('p.name strong', 0).text() }
        imageUrl { $('img', 0).getAttribute('src') }
        title(required: false) { $('p em', 0).text() }
        links { $('p a') }
    }

    def twitter() {
        for (def link in links) {
            if ( link.getAttribute('href').indexOf('twitter.com') != -1 ) {
                return link.text()
            }
        }
    }

    def company() {
        for (def link in links) {
            if ( link.getAttribute('href').indexOf('twitter.com') == -1 ) {
                return link.text()
            }
        }
    }
}
