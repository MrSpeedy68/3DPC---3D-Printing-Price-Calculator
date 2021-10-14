package controllers

import javafx.beans.property.SimpleStringProperty
import models.MaterialModel
import models.PrinterModel
import models.UserModel
import mu.KotlinLogging
import tornadofx.*
import screens.MaterialMenuScreen
import screens.MenuScreen
import screens.PrintersMenuScreen
import screens.UserScreen

class MenuUIController : Controller() {

    val logger = KotlinLogging.logger {}
    var totalPrintCost: Float = 0.0f
    var calculationController = CalculationController()
    var totalText = SimpleStringProperty("Total Printing Costs : $totalPrintCost")

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

    fun loadingCalculation(aMat: MaterialModel, aPrinter: PrinterModel, aUser: UserModel, modelWeight: Int, hours: Int, minutes: Int) : String {
        totalPrintCost = calculationController.TotalPrintCost(aMat, aPrinter, aUser,
            modelWeight, hours, minutes)

         return "Total Printing Costs : $totalPrintCost"
    }
}