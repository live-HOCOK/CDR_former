package exporter

import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class ExportToCSV {

    fun createCSV(data: String, operator: String) {
        val dateFormatter = SimpleDateFormat("ddMMYYYY_HHmm")
        val fileName = "${operator}_${dateFormatter.format(Calendar.getInstance().time)}.csv"
        val filePath = "${showChooserDirectory()}/$fileName"
        val writer: BufferedWriter = Files.newBufferedWriter(Paths.get(filePath))
        val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT)
        csvPrinter.printRecord(data)
        csvPrinter.flush()
        csvPrinter.close()
    }

    private fun showChooserDirectory(): String {
        return DirectoryChooser().showDialog(Stage()).path
    }
}