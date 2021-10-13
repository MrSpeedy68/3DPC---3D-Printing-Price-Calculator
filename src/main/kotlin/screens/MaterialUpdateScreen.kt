package screens

import controllers.MaterialUIController
import tornadofx.*

class MaterialUpdateScreen : View("Update Material") {

    val materialUIController : MaterialUIController by inject()

    override val root = form {
        setPrefSize(600.0, 400.0)

        text("")
        button("Close") {

            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    materialUIController.loadingCloseUpdate()
                }
            }
        }
    }
}