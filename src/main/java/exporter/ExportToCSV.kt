package exporter

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class ExportToCSV {

    fun createCSV(data: String, operator: String) {
        val dateFormater = SimpleDateFormat("ddMMYYYY_HHmm")
        val fileName = "${operator}_${dateFormater.format(Calendar.getInstance().time)}.csv"
        val filePath = "/Users/livehocok/Documents/$fileName"
        val writer = Files.newBufferedWriter(Paths.get(filePath))
        val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT)
        csvPrinter.printRecord(data)
        csvPrinter.flush()
        csvPrinter.close()
    }

}