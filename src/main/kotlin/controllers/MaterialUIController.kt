package controllers

import models.MaterialJSONStore
import models.MaterialModel
import mu.KotlinLogging
import screens.*
import tornadofx.*

class MaterialUIController : Controller() {

    var materials = MaterialJSONStore()
    var logger = KotlinLogging.logger {}

    fun add(_name : String, _type : String, _weight : Int, _price : Double){

        var aMaterial = MaterialModel(materialName = _name, materialType = _type, materialWeight = _weight, materialPrice = _price)
        materials.create(aMaterial)
        logger.info("Material Added")
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
            find(MaterialMenuScreen::class).replaceWith(MaterialListScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenDelete() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MaterialDeleteScreen::class,sizeToScene = true, centerOnScreen = true)
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

    fun loadingCloseDelete() {
        runLater {
            find(MaterialDeleteScreen::class).replaceWith(MaterialMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseMenu() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }


}