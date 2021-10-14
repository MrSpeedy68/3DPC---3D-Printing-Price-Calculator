package controllers

import models.MaterialModel
import models.PrinterModel
import models.UserModel

class CalculationController {


    //Add A Markup % ontop of the material
    fun TotalFilamentCost(aMat: MaterialModel, modelWeight: Int) : Float {
        var pricePerGram = aMat.materialPrice / aMat.materialWeight

        var totalFilamentCost = pricePerGram * modelWeight

        totalFilamentCost = RoundToTwoDecimalPlaces(totalFilamentCost)

        println("The Price in material for this print is : $totalFilamentCost")

        return totalFilamentCost
    }

    fun ElectricityCost(aPrinter: PrinterModel, hours: Int, minutes: Int, aUser: UserModel) : Float {

        var printerkwh : Float = aPrinter.wattUsage.toFloat()
        var kwhUsed = printerkwh / 1000
        var totalElectricityCost = ((kwhUsed * GetTimeInHoursDecimal(hours, minutes)) * aUser.energyCost).toFloat()

        totalElectricityCost = RoundToTwoDecimalPlaces(totalElectricityCost)

        println("The Total Electricity Cost for this print is : ${aUser.currency} $totalElectricityCost")

        return totalElectricityCost
    }

    fun PrinterCosts(aPrinter: PrinterModel, hours: Int, minutes: Int) : Float {
        var monthsToHours = aPrinter.investmentReturn * 720 //Turn Months into days x30 and days into hours x24 which equals 720
        var printerCosts: Float = ((aPrinter.printerPrice / monthsToHours) * GetTimeInHoursDecimal(hours, minutes)).toFloat()

        printerCosts = RoundToTwoDecimalPlaces(printerCosts)

        println("The total Printer Depriciation Cost for this print is : $printerCosts")

        return  printerCosts
    }

    fun TotalPrintCost(aMat: MaterialModel, aPrinter: PrinterModel, aUser: UserModel, modelWeight: Int, hours: Int, minutes: Int) : Float {
        var totalPrintCost = TotalFilamentCost(aMat,modelWeight) + ElectricityCost(aPrinter, hours, minutes, aUser) + PrinterCosts(aPrinter, hours, minutes)

        totalPrintCost = RoundToTwoDecimalPlaces(totalPrintCost)

        println("The total cost for this print is : ${aUser.currency} $totalPrintCost")

        return totalPrintCost
    }

//Helper Functions
    fun GetTimeInHoursDecimal(hours: Int, minutes: Int) : Float {
        var tempHours : Float = (minutes / 60).toFloat()

        return hours + tempHours
    }

    fun RoundToTwoDecimalPlaces(num: Float): Float {

        return String.format("%.2f", num).toFloat()
    }
}