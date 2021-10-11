package controllers

import models.MaterialModel
import models.PrinterModel
import models.UserModel
import org.wit.materialController
import org.wit.printerController
import kotlin.math.round

class CalculationController {

    var modelWeight: Double = 0.0
    var modelHours: Int = 0
    var modelMinutes: Int = 0
    var aMat = MaterialModel()
    var aPrinter = PrinterModel()
    var user = UserModel("Adrian",15.50,0.14,"$")

    fun GetPrintInformation() {
        println("Enter How Much Material The Print Will Take: ")
        modelWeight = readLine()?.toDouble()!!

        println()
        println("Enter How Long The Print Will Take In Hours: ")
        modelHours = readLine()?.toInt()!!

        println()
        println("Enter How Long The Print Will Take In Minutes: ")
        modelMinutes = readLine()?.toInt()!!

        println()
        println("Enter Which Material ID You are Using: ")
        materialController.list()
        var matId = readLine()?.toLong()!!
        aMat = materialController.search(matId)!!

        println()
        println("Enter Which Printer ID You are Using: ")
        printerController.list()
        var printerId = readLine()?.toLong()!!
        aPrinter = printerController.search(printerId)!!

        println("Model Weight: $modelWeight Model Time: $modelHours H and $modelMinutes m")
        println("Material $aMat")
        println("Printer $aPrinter")
        println("User $user")

        TotalPrintCost(TotalFilamentCost(),ElectricityCost(),PrinterCosts())
    }

    //Add A Markup % ontop of the material
    fun TotalFilamentCost() : Double {
        var pricePerGram = aMat.materialPrice / aMat.materialWeight

        var totalFilamentCost = pricePerGram * modelWeight

        totalFilamentCost = RoundToTwoDecimalPlaces(totalFilamentCost)

        println("The Price in material for this print is : ${user.currency} $totalFilamentCost")

        return totalFilamentCost
    }

    fun ElectricityCost() : Double {

        var printerkwh : Double = aPrinter.wattUsage.toDouble()
        var kwhUsed = printerkwh / 1000
        var totalElectricityCost = (kwhUsed * GetTimeInHoursDecimal(modelHours, modelMinutes)) * user.energyCost

        totalElectricityCost = RoundToTwoDecimalPlaces(totalElectricityCost)

        println("The Total Electricity Cost for this print is : ${user.currency} $totalElectricityCost")

        return totalElectricityCost
    }

    fun PrinterCosts() : Double {
        var monthsToHours = aPrinter.investmentReturn * 720 //Turn Months into days x30 and days into hours x24 which equals 720
        var printerCosts = (aPrinter.printerPrice / monthsToHours) * GetTimeInHoursDecimal(modelHours, modelMinutes)

        printerCosts = RoundToTwoDecimalPlaces(printerCosts)

        println("The total Printer Depriciation Cost for this print is : ${user.currency} $printerCosts")

        return  printerCosts
    }

    fun TotalPrintCost(filamentCost: Double, eneryCost: Double, printerCost: Double) {
        var totalPrintCost = filamentCost + eneryCost + printerCost

        totalPrintCost = RoundToTwoDecimalPlaces(totalPrintCost)

        println("The total cost for this print is : ${user.currency} $totalPrintCost")
    }

//Helper Functions
    fun GetTimeInHoursDecimal(hours: Int, minutes: Int) : Double {
        var tempHours : Double = (minutes / 60).toDouble()

        return hours + tempHours
    }

    fun RoundToTwoDecimalPlaces(num:Double): Double {
        val roundedNum:Double = String.format("%.2f", num).toDouble()

        return  roundedNum
    }
}