package controllers

import javafx.print.Printer
import models.MaterialModel
import models.PrinterJSONStore
import models.PrinterModel
import mu.KotlinLogging
import screens.*
import tornadofx.Controller
import tornadofx.runLater

class PrinterUIController : Controller() {

    var printers = PrinterJSONStore()
    var logger = KotlinLogging.logger {}

    var selectedPrinter = PrinterModel()

    var menuScreen = MenuScreen()

    fun add(_name: String, _price : Float, _watts : Int, _investment : Int) {
        var aPrinter = PrinterModel(printerName = _name, printerPrice = _price, wattUsage = _watts, investmentReturn = _investment)
        printers.create(aPrinter)

        menuScreen.printerData.add(aPrinter)
    }

    fun delete(aPrinter: PrinterModel) {
        printers.delete(aPrinter)
    }

    fun update(aPrinter: PrinterModel, updatedprinter: PrinterModel) {
        printers.update(aPrinter,updatedprinter)
    }

    //==============Open============
    fun loadingOpenAdd() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(PrinterAddScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenList() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(PrinterListScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenUpdate() {
        runLater {
            find(PrinterListScreen::class).replaceWith(PrinterUpdateScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    //===========Close=============
    fun loadingCloseAdd() {
        runLater {
            find(PrinterAddScreen::class).replaceWith(PrintersMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseList() {
        runLater {
            find(PrinterListScreen::class).replaceWith(PrintersMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseUpdate() {
        runLater {
            find(PrinterUpdateScreen::class).replaceWith(PrintersMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseMenu() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }
}