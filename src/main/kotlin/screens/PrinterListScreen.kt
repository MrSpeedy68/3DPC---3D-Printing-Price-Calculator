package screens

import controllers.PrinterUIController
import models.MaterialModel
import models.PrinterModel
import tornadofx.*

class PrinterListScreen : View("List of Printers") {

    val printerUIController : PrinterUIController by inject()
    val tableContent = printerUIController.printers.findAll()
    val data = tableContent.observable()

    override val root = vbox {
        setPrefSize(600.0, 200.0)
        text("List of All Printers")
        tableview(data) {
            readonlyColumn("ID", PrinterModel::printerId)
            readonlyColumn("NAME", PrinterModel::printerName)
            readonlyColumn("PRICE", PrinterModel::printerPrice)
            readonlyColumn("WATT USAGE", PrinterModel::wattUsage)
            readonlyColumn("RETURN ON INVESTMENT", PrinterModel::investmentReturn)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    printerUIController.loadingCloseList()
                }
            }
        }
    }
}