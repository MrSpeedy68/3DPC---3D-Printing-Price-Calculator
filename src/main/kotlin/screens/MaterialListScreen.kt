package screens

import controllers.MaterialUIController
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import models.MaterialJSONStore
import tornadofx.*
import models.MaterialModel

class MaterialListScreen : View("Material List") {

    val materialUIController : MaterialUIController by inject()
    val model = ViewModel()

    var tableContent = materialUIController.materials.findAll()
    var data = tableContent.observable()

    var searchResult = model.bind { SimpleStringProperty("None") }

    var selectedMat = MaterialModel()

    var searchString = model.bind { SimpleStringProperty("Search Here") }

    override val root = form {
        setPrefSize(600.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {

            field("Search by Name") {
                textfield(searchString)
            }

            button("Search") {
                isDefaultButton = true
                action {
                    println(materialUIController.materials.search(searchString.value))
                    searchResult.value = materialUIController.materials.search(searchString.value).toString()
                }
            }

            text(searchResult) {

            }
        }


        tableview(data) {
            readonlyColumn("ID", MaterialModel::materialId)
            readonlyColumn("NAME", MaterialModel::materialName)
            readonlyColumn("TYPE", MaterialModel::materialType)
            readonlyColumn("WEIGHT", MaterialModel::materialWeight)
            readonlyColumn("PRICE", MaterialModel::materialPrice)

            // click to select
            onUserSelect(1) { aMat: MaterialModel ->
                println(aMat)
                selectedMat = aMat
                refresh()
            }
        }

        text("")
        button("Delete") {
            isDefaultButton = true
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    materialUIController.delete(selectedMat)
                }
            }
        }

        text("")
        button("Update") {
            isDefaultButton = true
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    materialUIController.selectedMaterial = selectedMat
                    materialUIController.loadingOpenUpdate()
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
                    materialUIController.loadingCloseList()
                }
            }
        }
    }
}