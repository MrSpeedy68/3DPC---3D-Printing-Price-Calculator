package screens

import controllers.PrinterUIController
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import models.PrinterModel
import tornadofx.*

class PrinterUpdateScreen : View("Update a Printer") {

    val printerUIController : PrinterUIController by inject()
    val model = ViewModel()

    val _name = model.bind { SimpleStringProperty() }
    val _price = model.bind { SimpleFloatProperty() }
    val _watts = model.bind { SimpleIntegerProperty() }
    val _investment = model.bind { SimpleIntegerProperty() }



    override val root = form {
        setPrefSize(600.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Printer Name") {
                textfield(_name).required()
            }
            field("Printer Price") {
                textfield(_price).required()
            }
            field("Printer Watt Usage") {
                textfield(_watts).required()
            }
            field("Investment Return on Printer in Months") {
                textfield(_investment).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        val aPrinter = PrinterModel(printerName = _name.value.toString(), printerPrice = _price.value.toFloat(), wattUsage = _watts.value.toInt(), investmentReturn = _investment.value.toInt() )

                        printerUIController.update(printerUIController.selectedPrinter, aPrinter)
                        printerUIController.loadingCloseUpdate()

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
                        printerUIController.loadingCloseUpdate()
                    }
                }
            }
        }
    }
}