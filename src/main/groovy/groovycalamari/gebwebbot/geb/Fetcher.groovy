package groovycalamari.gebwebbot.geb

import geb.Browser
import org.openqa.selenium.WebDriver
import groovy.transform.CompileStatic

@CompileStatic
trait Fetcher {

    WebDriver driver

    Browser browser = new Browser()

    void setDriver(WebDriver driver) {
        this.driver = driver
        if ( driver ) {
            browser.config.driver = driver
        }
    }

    @SuppressWarnings('Println')
    void log(String msg) {
        println msg
    }
}
