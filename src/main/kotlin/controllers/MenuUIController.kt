package controllers

import models.MaterialJSONStore
import mu.KotlinLogging
import tornadofx.*
import screens.MaterialMenuScreen
import screens.MenuScreen
import screens.PrintersMenuScreen
import screens.UserScreen

class MenuUIController : Controller() {

    val materials = MaterialJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching 3D Printing Price Calculator TornadoFX UI App" }
    }

    fun loadingMaterialScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(MaterialMenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingPrinterScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(PrintersMenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingUserScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UserScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCalculationScreen() {

    }

    fun closeMaterial() {
        runLater {
            find(MaterialMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closePrinter() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeUser() {
        runLater {
            find(UserScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }
}