package screens

import controllers.PrinterUIController
import javafx.scene.paint.Color
import models.PrinterModel
import tornadofx.*

class PrinterListScreen : View("List of Printers") {

    val printerUIController : PrinterUIController by inject()
    val tableContent = printerUIController.printers.findAll()
    val data = tableContent.observable()

    var selectedPrinter = PrinterModel()

    override val root = vbox {
        setPrefSize(600.0, 400.0)
        text("List of All Printers")
        tableview(data) {
            readonlyColumn("ID", PrinterModel::printerId)
            readonlyColumn("NAME", PrinterModel::printerName)
            readonlyColumn("PRICE", PrinterModel::printerPrice)
            readonlyColumn("WATT USAGE", PrinterModel::wattUsage)
            readonlyColumn("RETURN ON INVESTMENT", PrinterModel::investmentReturn)

            //Double click to select printer
            onUserSelect { aPrinter ->
                println(aPrinter)
                selectedPrinter = aPrinter
            }
        }

        text("")
        button("Delete") {
            isDefaultButton = true
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    printerUIController.delete(selectedPrinter)
                }
            }
        }

        text("")
        button("Update") {
            isDefaultButton = true
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    printerUIController.selectedPrinter = selectedPrinter
                    printerUIController.loadingOpenUpdate()
                }
            }
        }

        text("")
        button("Close") {
            style {
                backgroundColor += Color.INDIANRED
            }
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    printerUIController.loadingCloseList()
                }
            }
        }
    }
}