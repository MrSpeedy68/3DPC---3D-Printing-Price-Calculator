package views

import models.PrinterJSONStore
import models.PrinterModel

class PrinterView {

    fun showPrinter(printer: PrinterModel) {
        if(printer != null)
            println("Printer Details [ $printer ]")
        else
            println("Printer Not Found...")
    }

    fun addPrinterData(printer: PrinterModel) : Boolean {
        if(printer.printerName.isEmpty()) {
            return false
        }
        if(printer.printerPrice <= 0) {
            return false
        }
        if(printer.wattUsage <= 0) {
            return false
        }
        if(printer.investmentReturn <= 0) {
            return false
        }

        return true
    }

    fun updatePrinterData(printer: PrinterModel) : Boolean {
        println("Update Printer")
        println()

        var tempPrinterName: String?
        var tempPrinterPrice: Float?
        var tempWattUsage: Int?
        var tempInvestmentReturn: Int?

        if(printer != null) {
            println("Enter a new Name for [ ${printer.printerName} ] : ")
            tempPrinterName = readLine()!!
            println("Enter a new Price for [ ${printer.printerPrice} ] : ")
            tempPrinterPrice = readLine()?.toFloat()!!
            println("Enter a new Watt Usage for [ ${printer.wattUsage} ] : ")
            tempWattUsage = readLine()?.toInt()!!
            println("Enter a new Price for [ ${printer.investmentReturn} ] : ")
            tempInvestmentReturn = readLine()?.toInt()!!

            if(tempPrinterName.isNotEmpty()) {
                printer.printerName = tempPrinterName
                printer.printerPrice = tempPrinterPrice
                printer.wattUsage = tempWattUsage
                printer.investmentReturn = tempInvestmentReturn
                return true
            }
        }
        return false
    }

    fun listAllPrinter(printers : PrinterJSONStore) {
        println("List All Printers")
        println()
        printers.logAll()
        println()
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted Id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}