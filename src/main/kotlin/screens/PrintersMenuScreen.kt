package screens

import controllers.MenuUIController
import javafx.geometry.Orientation
import tornadofx.*

class PrintersMenuScreen : View("Printer Menu") {

    val menuUIController : MenuUIController by inject()

    //val tableContent = placemarkUIController.placemarks.findAll()
    //val data = tableContent.observable()


    override val root = form {
        setPrefSize(400.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Printer") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingMaterialScreen()
                    }
                }
            }
            text("")
            button("List All Printers") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingPrinterScreen()
                    }
                }
            }
            text("")
            button("Update Printer") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingUserScreen()
                    }
                }
            }
            text("")
            button("Delete Printer") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.closeMaterial()
                    }
                }
            }
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.closePrinter()
                    }
                }
            }
        }
    }
}