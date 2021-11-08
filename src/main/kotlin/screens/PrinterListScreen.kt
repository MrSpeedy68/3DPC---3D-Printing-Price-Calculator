package screens

import controllers.PrinterUIController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import models.PrinterModel
import tornadofx.*

class PrinterListScreen : View("List of Printers") {

    val printerUIController : PrinterUIController by inject()
    val model = ViewModel()

    val tableContent = printerUIController.printers.findAll()
    val data = tableContent.observable()

    var searchResult = model.bind { SimpleStringProperty("None") }

    var selectedPrinter = PrinterModel()

    var searchString = model.bind { SimpleStringProperty("Search Here") }

    override val root = form {
        setPrefSize(600.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {

            field("Search by Name") {
                textfield(searchString)
            }

            button("Search") {
                isDefaultButton = true
                action {
                    println(printerUIController.printers.search(searchString.value))
                    searchResult.value = printerUIController.printers.search(searchString.value).toString()
                }
            }

            text(searchResult) {

            }
        }


        text("List of All Printers")
        tableview(data) {
            readonlyColumn("ID", PrinterModel::printerId)
            readonlyColumn("NAME", PrinterModel::printerName)
            readonlyColumn("PRICE", PrinterModel::printerPrice)
            readonlyColumn("WATT USAGE", PrinterModel::wattUsage)
            readonlyColumn("RETURN ON INVESTMENT", PrinterModel::investmentReturn)

            // click to select printer
            onUserSelect(1) { aPrinter ->
                println(aPrinter)
                selectedPrinter = aPrinter
                refresh()
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