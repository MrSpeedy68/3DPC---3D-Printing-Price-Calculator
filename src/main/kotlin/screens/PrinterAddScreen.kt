package screens

import controllers.PrinterUIController
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class PrinterAddScreen : View("Add a Printer") {

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
                        printerUIController.add(_name.value.toString(), _price.value.toFloat(), _watts.value.toInt(), _investment.value.toInt())
                    }
                }
            }
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        printerUIController.loadingCloseAdd()
                    }
                }
            }
        }
    }
}