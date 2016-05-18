import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
    timeout = 5
}

if(System.getProperty('phantomjs.binary.path')) {
    driver = new PhantomJSDriver()
}

if(System.getenv('phantomjs.binary.path') && !System.getProperty('phantomjs.binary.path')) {
    System.setProperty('phantomjs.binary.path', System.getenv('phantomjs.binary.path'))
    driver = new PhantomJSDriver()
}

environments {
    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }
    }

    // run via “./gradlew phantomJsTest”
    phantomJs {
        driver = { new PhantomJSDriver() }
    }
}