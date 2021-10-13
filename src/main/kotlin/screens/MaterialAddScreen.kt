package screens

import controllers.MaterialUIController
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class MaterialAddScreen : View("Add a Material"){

    val materialUIController : MaterialUIController by inject()
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _type = model.bind { SimpleStringProperty() }
    val _weight = model.bind { SimpleIntegerProperty() }
    val _price = model.bind { SimpleDoubleProperty() }

    override val root = form {
        setPrefSize(600.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Material Name") {
                textfield(_name).required()
            }
            field("Material Type") {
                textfield(_type).required()
            }
            field("Material Weight") {
                textfield(_weight).required()
            }
            field("Material Price") {
                textfield(_price).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.add(_name.value.toString(), _type.value.toString(), _weight.value.toInt(), _price.value.toDouble())
                    }
                }
            }
            text("")
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingCloseAdd()
                    }
                }
            }
        }
    }
}
