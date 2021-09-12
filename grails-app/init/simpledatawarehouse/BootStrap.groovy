package simpledatawarehouse

class BootStrap {
    final def URL = "http://adverity-challenge.s3-website-eu-west-1.amazonaws.com/PIxSyyrIKFORrCXfMYqZBI.csv"

    def init = { ctx ->
        log.debug('Start Bootstrap')
        environments {
            production {
                ctx.setAttribute("env", "prod")
            }
            development {
                ctx.setAttribute("env", "dev")
                parseCsvData()
            }
        }
//        parseCsvData()
    }

    private void parseCsvData() {
        File file = createTmpFileWithCsvData()
        parseCsvFile(file)
        file.deleteOnExit()
    }

    private File createTmpFileWithCsvData() {
        log.debug('Creating tmp file')
        File file = new File("adverity.csv")
        log.debug('Retrieving CSV file')
        FileOutputStream fos = new FileOutputStream(file)
        fos.write(new URL(URL).getBytes())
        fos.close()
        file
    }

    private void parseCsvFile(File file) {
        log.debug('Start parsing file')
        def isFirstLine = true
        file.eachLine() {
            def split = it.split(",")
            if (!isFirstLine) {
                def dateSplit = split[2].split("/").collect { it.toInteger() }
                Data.withTransaction {
                    parseCsvLineToDataDomain(split, dateSplit)
                }
            }
            isFirstLine = false
        }
        log.debug("Finished parsing")
    }

    private void parseCsvLineToDataDomain(String[] split, List<Integer> dateSplit) {
        log.debug("Parsing CSV Data {$split}")
        def data = new Data(
                datasource: split[0],
                campaign: split[1],
                daily: new Date(dateSplit[2], dateSplit[1], dateSplit[0]),
                clicks: split[3].toInteger(),
                impressions: split[4].toInteger()
        )
        log.debug("Saving Data $data")
        data.save(failOnError: true, flush: true)
    }
    def destroy = {
    }
}
