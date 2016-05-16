package groovycalamari.gebwebbot.output

trait Csvable extends Outputable {
    static final String CSV_SUFFIX = '.csv'

    @SuppressWarnings('JavaIoPackageAccess')
    static saveCollectionAsCsv(String filename,
                               String destinationPath,
                               Collection<Csvable> objects,
                               String delimiter) {

        def csvName = filename?.endsWith(CSV_SUFFIX) ? "${filename}" : "${filename}${CSV_SUFFIX}"
        def path = destinationPath?.endsWith('/') ? destinationPath : "${destinationPath}/"
        def csvNameWithPath = "$path/${csvName}"

        def f = new File(csvNameWithPath)
        if (objects) {
            f.withWriter('UTF-8') { writer ->
                def headers = objects[0].propertiesToPersistMap().keySet().join(delimiter) + '\n'
                writer.write(headers)
                for (int i = 0; i < objects.size(); i++) {
                    Csvable obj = objects[i]
                    def m = obj.propertiesToPersistMap()
                    writer.write(m.values().join(delimiter) + '\n')
                }
            }
        }
    }
}
