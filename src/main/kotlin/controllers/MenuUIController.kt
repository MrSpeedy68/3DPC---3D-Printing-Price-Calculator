package controllers

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import models.*
import mu.KotlinLogging
import tornadofx.*
import screens.MaterialMenuScreen
import screens.MenuScreen
import screens.PrintersMenuScreen
import screens.UserScreen

class MenuUIController : Controller() {

    val logger = KotlinLogging.logger {}

    var calculationController = CalculationController()

    val printers = PrinterJSONStore()
    var printerData = printers.findAllObservable()

    val materials = MaterialJSONStore()
    var materialData = materials.findAll()


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

    fun loadingTotalCost(aMat: MaterialModel, aPrinter: PrinterModel, aUser: UserModel, modelWeight: Int, hours: Int, minutes: Int) : String {
        var totalPrintCost: Float = calculationController.TotalPrintCost(aMat, aPrinter, aUser,
            modelWeight, hours, minutes)

         return "Total Printing Cost : ${aUser.currency} $totalPrintCost"
    }

    fun loadingTotalMaterialCost(aMat: MaterialModel, modelWeight: Int, aUser: UserModel) :String {
        var totalMaterialCost: Float = calculationController.TotalFilamentCost(aMat,modelWeight)

        return "Total Material Cost : ${aUser.currency} $totalMaterialCost"
    }

    fun loadingTotalEnergyCost(aPrinter: PrinterModel, hours: Int, minutes: Int, aUser: UserModel) :String {
        var totalEnergyCost: Float = calculationController.ElectricityCost(aPrinter,hours,minutes,aUser)

        return "Total Energy Cost : ${aUser.currency} $totalEnergyCost"
    }

    fun loadingTotalPrinterCost(aPrinter: PrinterModel,hours: Int, minutes: Int, aUser: UserModel) : String {
        var totalPrinterCost: Float = calculationController.PrinterCosts(aPrinter, hours, minutes)

        return "Total Printer Cost : ${aUser.currency} $totalPrinterCost"
    }

    fun reloadCombos() : ObservableList<PrinterModel> {
        printerData = printers.findAllObservable()

        return printerData
    }
}