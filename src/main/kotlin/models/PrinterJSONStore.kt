package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import views.PrinterView
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE_PRINTER = "printer.json"
val gsonBuilder_Printer = GsonBuilder().setPrettyPrinting().create()
val listType_Printer = object : TypeToken<java.util.ArrayList<PrinterModel>>() {}.type

val printerView = PrinterView()

fun generateRandomIdPrinter(): Long {
    return Random().nextLong()
}

class PrinterJSONStore : PrinterStore {

    var printers = mutableListOf<PrinterModel>()

    init {
        if (exists(JSON_FILE_PRINTER)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PrinterModel> {
        return printers
    }

    //Returns an observable list of all the printers
    override fun findAllObservable() : ObservableList<PrinterModel> {
        var obsList = FXCollections.observableList(printers)

        return obsList
    }

    override fun findOne(id: Long) : PrinterModel? {
        var foundPrinter: PrinterModel? = printers.find { p -> p.printerId == id }
        return foundPrinter
    }

    override fun create(printer: PrinterModel) {
        printer.printerId = generateRandomIdPrinter()
        if(printerView.addPrinterData(printer)) {
            printers.add(printer)
            serialize()
            logger.info("Printer Added!!!")
        }
        else
            logger.info("Printer Not Added!!!")
    }

    override fun update(printer: PrinterModel, updatedPrinter: PrinterModel) {
        printer.printerName = updatedPrinter.printerName
        printer.printerPrice = updatedPrinter.printerPrice
        printer.wattUsage = updatedPrinter.wattUsage
        printer.investmentReturn = updatedPrinter.investmentReturn
        serialize()
        logger.info("Printer Updated!!!")
    }

    override fun delete(printer: PrinterModel) {
        printers.remove(printer)
        serialize()
        logger.info("Printer Deleted!!!")
    }

    internal fun logAll() {
        printers.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder_Printer.toJson(printers, listType_Printer)
        write(JSON_FILE_PRINTER, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_PRINTER)
        printers = Gson().fromJson(jsonString, listType_Printer)
    }
}