package screens

import controllers.PrinterUIController
import javafx.geometry.Orientation
import tornadofx.*

class PrintersMenuScreen : View("Printer Menu") {

    val printerUIController : PrinterUIController by inject()

    //val tableContent = placemarkUIController.placemarks.findAll()
    //val data = tableContent.observable()


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
            button("Update Printer") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        printerUIController.loadingOpenUpdate()
                    }
                }
            }
            text("")
            button("Delete Printer") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        printerUIController.loadingOpenDelete()
                    }
                }
            }
            text("")
            button("Close") {
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