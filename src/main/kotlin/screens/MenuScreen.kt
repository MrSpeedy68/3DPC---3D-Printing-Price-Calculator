package screens

import controllers.MenuUIController
import javafx.application.Platform
import javafx.geometry.Orientation
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