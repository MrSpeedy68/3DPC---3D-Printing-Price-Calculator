package controllers;

import models.MaterialModel
import models.PrinterJSONStore
import models.PrinterMemStore
import models.PrinterModel
import mu.KotlinLogging
import views.PrinterView


class PrinterController {

    //val printers = PrinterMemStore()

    val printers = PrinterJSONStore()
    val printerView = PrinterView()
    val logger = KotlinLogging.logger {}

    fun add() {
        var aPrinter = PrinterModel()

        if(printerView.addPrinterData(aPrinter)) {
            printers.create(aPrinter)
        }
        else
            logger.info("Printer Not Added!!!")
    }

    fun list() {
        printerView.listAllPrinter(printers)
    }

    fun update() {
        printerView.listAllPrinter(printers)
        var searchId = printerView.getId()
        val aPrinter = search(searchId)

        if(aPrinter != null) {
            if(printerView.updatePrinterData(aPrinter)) {
                printers.update(aPrinter)
                printerView.showPrinter(aPrinter)
                logger.info("Printer Updated : [ $aPrinter ]")
            }
            else
                logger.info("Printer Not Updated...")
        }
        else
            logger.info("Printer Not Updated...")
    }

    fun search() {
        val aPrinter = search(printerView.getId())!!
        printerView.showPrinter(aPrinter)
    }

    fun search(id: Long) : PrinterModel? {
        var foundPrinter = printers.findOne(id)
        return foundPrinter
    }

    fun dummyData() {
        printers.create(PrinterModel(printerName = "Elegoo Mars 2 Pro", printerPrice = 249.95, wattUsage = 50, investmentReturn = 24))
        printers.create(PrinterModel(printerName = "Ender 3 Pro", printerPrice = 149.95, wattUsage = 150, investmentReturn = 12))
        printers.create(PrinterModel(printerName = "CR10 V3", printerPrice = 460.0, wattUsage = 200, investmentReturn = 24))
    }

}
