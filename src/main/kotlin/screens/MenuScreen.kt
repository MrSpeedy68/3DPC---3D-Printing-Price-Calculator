package screens

import controllers.MenuUIController
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import models.MaterialJSONStore
import models.MaterialModel
import models.PrinterJSONStore
import models.PrinterModel
import tornadofx.*

class MenuScreen : View("3D Printing Price Calculator") {

    val menuUIController : MenuUIController by inject()

    override val root = form {
        setPrefSize(400.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Material Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingMaterialScreen()
                    }
                }
            }
            text("")
            button("Printer Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingPrinterScreen()
                    }
                }
            }
            text("")
            button("User Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingUserScreen()
                    }
                }
            }

            //-----------Combo Box----------------\\
            text("")
            val materials = MaterialJSONStore()
            val data = materials.findAllObservable()
//Make a function that refreshes the list on opening the main menu
            combobox<MaterialModel> {
                items = data
            }

            text("")
            val printers = PrinterJSONStore()
            val data2 = printers.findAllObservable()
//Make a function that refreshes the list on opening the main menu
            combobox<PrinterModel> {
                items = data2
            }









            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }
}