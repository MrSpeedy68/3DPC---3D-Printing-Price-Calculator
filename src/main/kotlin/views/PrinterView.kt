package views

import models.PrinterMemStore
import models.PrinterModel

class PrinterView {

    fun showPrinter(printer: PrinterModel) {
        if(printer != null)
            println("Printer Details [ $printer ]")
        else
            println("Printer Not Found...")
    }

    fun addPrinterData(printer: PrinterModel) : Boolean {
        print("Enter a Printer Name : ")
        printer.printerName = readLine()!!

        print("Enter Printer Price : ")
        printer.printerPrice = readLine()?.toDouble()!!

        print("Enter Printers Power Usage in Watts : ")
        printer.wattUsage = readLine()?.toInt()!!

        print("In How Many Months Do You Want To Repay The Printer : ")
        printer.investmentReturn = readLine()?.toInt()!!

        return printer.printerName.isNotEmpty()
    }

    fun updatePrinterData(printer: PrinterModel) : Boolean {
        println("Update Printer")
        println()

        var tempPrinterName: String?
        var tempPrinterPrice: Double?
        var tempWattUsage: Int?
        var tempInvestmentReturn: Int?

        if(printer != null) {
            println("Enter a new Name for [ ${printer.printerName} ] : ")
            tempPrinterName = readLine()!!
            println("Enter a new Price for [ ${printer.printerPrice} ] : ")
            tempPrinterPrice = readLine()?.toDouble()!!
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

    fun listAllPrinter(printers : PrinterMemStore) {
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