package views

import models.PrinterJSONStore
import models.PrinterModel

class PrinterView {

    //Check if all printer data is valid before adding
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

    //Check if all printer data to update is valid
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
}