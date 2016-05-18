package groovycalamari.gebwebbot.geb

import geb.Browser
import groovy.transform.CompileStatic

@CompileStatic
trait Fetcher {

    Browser browser = new Browser()

    @SuppressWarnings('Println')
    void log(String msg) {
        println msg
    }
}
