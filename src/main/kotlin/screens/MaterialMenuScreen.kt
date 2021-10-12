package screens

import controllers.MenuUIController
import javafx.geometry.Orientation
import tornadofx.*

class MaterialMenuScreen : View("Material Menu") {

    val menuUIController : MenuUIController by inject()

    //val tableContent = placemarkUIController.placemarks.findAll()
    //val data = tableContent.observable()


    override val root = form {
        setPrefSize(400.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Material") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingMaterialScreen()
                    }
                }
            }
            text("")
            button("List All Materials") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingPrinterScreen()
                    }
                }
            }
            text("")
            button("Update Material") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingUserScreen()
                    }
                }
            }
            text("")
            button("Delete Material") {
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
                        menuUIController.closeMaterial()
                    }
                }
            }
        }
    }
}