package screens

import controllers.MaterialUIController
import javafx.scene.paint.Color
import tornadofx.*
import models.MaterialModel

class MaterialListScreen : View("Material List") {

    val materialUIController : MaterialUIController by inject()
    val tableContent = materialUIController.materials.findAll()
    val data = tableContent.observable()

    var selectedMat = MaterialModel()


    override val root = vbox {
        setPrefSize(600.0, 400.0)
        tableview(data) {
            readonlyColumn("ID", MaterialModel::materialId)
            readonlyColumn("NAME", MaterialModel::materialName)
            readonlyColumn("TYPE", MaterialModel::materialType)
            readonlyColumn("WEIGHT", MaterialModel::materialWeight)
            readonlyColumn("PRICE", MaterialModel::materialPrice)

            onUserSelect { aMat: MaterialModel ->
                println(aMat)
                selectedMat = aMat
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