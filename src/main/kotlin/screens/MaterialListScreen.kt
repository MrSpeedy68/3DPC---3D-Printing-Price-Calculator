package screens

import controllers.MaterialUIController
import tornadofx.*
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import models.MaterialModel
import tornadofx.Stylesheet.Companion.tableView

class MaterialListScreen : View("Material List") {

    val materialUIController : MaterialUIController by inject()
    val tableContent = materialUIController.materials.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", MaterialModel::materialId)
            readonlyColumn("NAME", MaterialModel::materialName)
            readonlyColumn("TYPE", MaterialModel::materialType)
            readonlyColumn("WEIGHT", MaterialModel::materialWeight)
            readonlyColumn("PRICE", MaterialModel::materialPrice)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    materialUIController.loadingCloseList()
                }
            }
        }
    }
}