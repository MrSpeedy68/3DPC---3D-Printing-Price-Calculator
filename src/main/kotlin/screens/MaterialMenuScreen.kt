package screens

import controllers.MaterialUIController
import javafx.geometry.Orientation
import tornadofx.*

class MaterialMenuScreen : View("Material Menu") {

    val materialUIController : MaterialUIController by inject()

    override val root = form {
        setPrefSize(400.0, 400.0)
        text("Material Menu")
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Material") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingOpenAdd()
                    }
                }
            }
            text("")
            button("List All Materials") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingOpenList()
                    }
                }
            }
            text("")
            button("Update Material") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingOpenUpdate()
                    }
                }
            }
            text("")
            button("Delete Material") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingOpenDelete()
                    }
                }
            }
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingCloseMenu()
                    }
                }
            }
        }
    }
}