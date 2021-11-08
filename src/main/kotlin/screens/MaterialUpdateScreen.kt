package screens

import controllers.MaterialUIController
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import models.MaterialModel
import tornadofx.*

class MaterialUpdateScreen : View("Update Material") {

    val materialUIController : MaterialUIController by inject()
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _type = model.bind { SimpleStringProperty() }
    val _weight = model.bind { SimpleIntegerProperty() }
    val _price = model.bind { SimpleFloatProperty() }

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
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        val aMaterial = MaterialModel(materialName = _name.value.toString(), materialType = _type.value.toString(), materialWeight = _weight.value.toInt(), materialPrice = _price.value.toFloat())

                        materialUIController.update(materialUIController.selectedMaterial,aMaterial)
                        materialUIController.loadingCloseUpdate()
                    }
                }
            }
            text("")
            button("Close") {
                style {
                    backgroundColor += Color.INDIANRED
                }
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        materialUIController.loadingCloseUpdate()
                    }
                }
            }
        }
    }
}