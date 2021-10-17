package screens

import controllers.PrinterUIController
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import tornadofx.*

class PrintersMenuScreen : View("Printer Menu") {

    val printerUIController : PrinterUIController by inject()

    override val root = form {
        setPrefSize(400.0, 400.0)
        text("Printer Menu")
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Printer") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        printerUIController.loadingOpenAdd()
                    }
                }
            }
            text("")
            button("List All Printers") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        printerUIController.loadingOpenList()
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
                        printerUIController.loadingCloseMenu()
                    }
                }
            }
        }
    }
}