package screens

import controllers.MaterialUIController
import controllers.PrinterUIController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class PrinterUpdateScreen : View("Update a Printer") {

    val printerUIController : PrinterUIController by inject()

    override val root = form {
        setPrefSize(600.0, 400.0)

        text("")
        button("Close") {

            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    printerUIController.loadingCloseUpdate()
                }
            }
        }
    }
}