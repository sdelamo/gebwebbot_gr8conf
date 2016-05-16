package groovycalamari.gebwebbot.output

import com.dd.plist.NSArray
import com.dd.plist.NSDictionary
import com.dd.plist.PropertyListParser

trait Plistable extends Outputable {
    static final String PLIST_SUFFIX = '.plist'

    @SuppressWarnings('JavaIoPackageAccess')
    static saveCollectionAsPlist(String filename, String destinationPath, Collection<Plistable> objects) {

        def plistName = filename?.endsWith(PLIST_SUFFIX) ? "${filename}" : "${filename}${PLIST_SUFFIX}"
        def path = destinationPath?.endsWith('/') ? destinationPath : "${destinationPath}/"
        def plistNameWithPath = "$path/${plistName}"

        NSArray root = new NSArray(objects.size())

        for ( int i = 0; i < objects.size(); i++ ) {
            Plistable obj = objects[i]
            def m = obj.propertiesToPersistMap()
            NSDictionary dict = new NSDictionary()
            m.each { k, v ->
                dict.put(k, v)
            }
            root.setValue(i, dict)
        }
        PropertyListParser.saveAsXML(root, new File(plistNameWithPath))
    }
}
