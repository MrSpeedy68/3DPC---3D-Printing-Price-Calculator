package models;
import models.PrinterModel
import models.PrinterStore
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PrinterMemStore : PrinterStore{

    val printers = ArrayList<PrinterModel>()

    override fun findAll(): List<PrinterModel> {
        return printers
    }

    override fun findOne(id: Long): PrinterModel? {
        var foundPrinter: PrinterModel? = printers.find { p -> p.printerId == id}
        return foundPrinter
    }

    override fun create(printer: PrinterModel) {
        printer.printerId = getId()
        printers.add(printer)
        logAll()
    }

    override fun update(printer: PrinterModel) {
        var foundPrinter = findOne(printer.printerId!!)
        if (foundPrinter != null) {
            foundPrinter.printerName = printer.printerName
            foundPrinter.printerPrice = printer.printerPrice
            foundPrinter.wattUsage = printer.wattUsage
            foundPrinter.investmentReturn = printer.investmentReturn
        }
    }

    internal fun logAll() {
        printers.forEach { logger.info("${it}") }
    }
}
