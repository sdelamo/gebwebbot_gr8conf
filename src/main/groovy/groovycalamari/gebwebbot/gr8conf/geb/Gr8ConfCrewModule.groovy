package groovycalamari.gebwebbot.gr8conf.geb

import geb.Module

class Gr8ConfCrewModule extends Module {
    static content = {
        name { $('p.name strong', 0).text() }
        imageUrl { $('img', 0).getAttribute('src') }
        title(required: false) { $('p em', 0).text() }
        twitter(required: false) { $('p a', href: contains('twitter.com')).text() }
        company(required: false) { $('p a', href: notContains('twitter.com')).text() }
    }
}
