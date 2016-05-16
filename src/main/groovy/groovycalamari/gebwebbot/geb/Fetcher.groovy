package groovycalamari.gebwebbot.geb

import geb.Browser
import geb.Configuration
import org.openqa.selenium.WebDriver
import groovy.transform.CompileStatic

@CompileStatic
trait Fetcher {

    Browser browser = new Browser()

    @SuppressWarnings('Println')
    void log(String msg) {
        println msg
    }
}
