package screens

import controllers.UserUIController
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class UserScreen : View("User Settings") {

    val userUIController : UserUIController by inject()
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _currency = model.bind { SimpleStringProperty() }
    val _labour = model.bind { SimpleDoubleProperty() }
    val _energy = model.bind { SimpleDoubleProperty() }

    override val root = form {
        setPrefSize(600.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Labour Cost") {
                textfield(_labour).required()
            }
            field("Energy Cost") {
                textfield(_energy).required()
            }
            field("Currency") {
                textfield(_currency).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        userUIController.add(_name.value.toString(),_labour.value.toDouble(),_energy.value.toDouble(),_currency.value.toString())
                    }
                }
            }
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        userUIController.closeUser()
                    }
                }
            }
        }
    }
}