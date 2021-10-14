package controllers

import models.PrinterJSONStore
import models.PrinterModel
import mu.KotlinLogging
import screens.*
import tornadofx.Controller
import tornadofx.runLater

class PrinterUIController : Controller() {

    var printers = PrinterJSONStore()
    var logger = KotlinLogging.logger {}

    fun add(_name: String, _price : Float, _watts : Int, _investment : Int) {
        var aPrinter = PrinterModel(printerName = _name, printerPrice = _price, wattUsage = _watts, investmentReturn = _investment)
        printers.create(aPrinter)
        logger.info("Printer Added")
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
            find(PrintersMenuScreen::class).replaceWith(PrinterUpdateScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingOpenDelete() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(PrinterDeleteScreen::class,sizeToScene = true, centerOnScreen = true)
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
            find(MaterialUpdateScreen::class).replaceWith(PrintersMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseDelete() {
        runLater {
            find(PrinterDeleteScreen::class).replaceWith(PrintersMenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadingCloseMenu() {
        runLater {
            find(PrintersMenuScreen::class).replaceWith(MenuScreen::class,sizeToScene = true, centerOnScreen = true)
        }
    }
}