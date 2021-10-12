package screens

import controllers.MenuUIController
import javafx.geometry.Orientation
import tornadofx.*

class UserScreen : View("User Settings") {

    val menuUIController : MenuUIController by inject()


    override val root = form {
        setPrefSize(400.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.closeUser()
                    }
                }
            }
        }
    }
}