package screens

import controllers.MaterialUIController
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

        button("Delete") {
            isDefaultButton = true
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    materialUIController.delete(selectedMat)
                }
            }
        }

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