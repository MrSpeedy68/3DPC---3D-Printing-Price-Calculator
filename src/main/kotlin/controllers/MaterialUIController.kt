package controllers

import models.MaterialJSONStore
import models.MaterialModel
import mu.KotlinLogging
import screens.*
import tornadofx.*

class MaterialUIController : Controller() {

    val materials = MaterialJSONStore()
    var selectedMaterial = MaterialModel()


    fun add(_name : String, _type : String, _weight : Int, _price : Float){

        val aMaterial = MaterialModel(materialName = _name, materialType = _type, materialWeight = _weight, materialPrice = _price)
        materials.create(aMaterial)
    }

    fun delete(aMat: MaterialModel) {
        materials.delete(aMat)
    }

    fun update(aMat: MaterialModel, updatedMat: MaterialModel) {
        materials.update(aMat,updatedMat)
    }



    //==============Open============
    fun loadingOpenAdd() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MaterialAddScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenList() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MaterialListScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenUpdate() {
        runLater {
            find(MaterialListScreen::class).replaceWith(MaterialUpdateScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }



    //===========Close=============
    fun loadingCloseAdd() {
        runLater {
            find(MaterialAddScreen::class).replaceWith(MaterialMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseList() {
        runLater {
            find(MaterialListScreen::class).replaceWith(MaterialMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseUpdate() {
        runLater {
            find(MaterialUpdateScreen::class).replaceWith(MaterialMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }


    fun loadingCloseMenu() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }


}