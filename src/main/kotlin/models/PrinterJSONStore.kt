package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import models.PrinterModel
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE_PRINTER = "printer.json"
val gsonBuilder_Printer = GsonBuilder().setPrettyPrinting().create()
val listType_Printer = object : TypeToken<java.util.ArrayList<PrinterModel>>() {}.type

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

    override fun findOne(id: Long) : PrinterModel? {
        var foundPrinter: PrinterModel? = printers.find { p -> p.printerId == id }
        return foundPrinter
    }

    override fun create(printer: PrinterModel) {
        printer.printerId = generateRandomIdPrinter()
        printers.add(printer)
        serialize()
    }

    override fun update(printer: PrinterModel) {
        var foundPrinter = findOne(printer.printerId!!)
        if (foundPrinter != null) {
            foundPrinter.printerName = printer.printerName
            foundPrinter.printerPrice = printer.printerPrice
            foundPrinter.wattUsage = printer.wattUsage
            foundPrinter.investmentReturn = printer.investmentReturn
        }
        serialize()
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