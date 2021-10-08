package org.wit

import models.MaterialModel
import models.PrinterModel
import models.UserModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

val materials = ArrayList<MaterialModel>()
val printers = ArrayList<PrinterModel>()
val user = UserModel()

fun main(args: Array<String>) {
    //logger.info { "Launching 3D Printing Price Calculator" }
    println("3D Printing Price Calculator App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addMaterial()
            2 -> addPrinter()
            3 -> listAllMaterials()
            4 -> listAllPrinters()
            5 -> updateMaterial()
            6 -> updatePrinter()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info {"Shutting Down 3DPC Console App"}
}

fun menu() : Int {

    var option : Int
    var input : String? = null

    println("===Main Menu===")
    println(" 1. Add Material")
    println(" 2. Add Printer")
    println(" 3. List All Materials")
    println(" 4. List All Printers")
    println(" 5. Update Material")
    println(" 6. Update Printer")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addMaterial() {
    var aMaterial = MaterialModel()
    println("Add Material")
    println()
    print("Enter a Material Name : ")
    aMaterial.materialName = readLine()!!

    println("Add a Material Type")
    println()
    print("Enter a Material Type : ")
    aMaterial.materialType = readLine()!!

    println("Add Material Weight in Grams")
    println()
    print("Enter Material Weight : ")
    var matWeight  = readLine()?.toInt()

    println("Add a Material Price")
    println()
    print("Enter a Material Price : ")
    var matPrice = readLine()?.toDouble()

    if(aMaterial.materialName.isNotEmpty() && aMaterial.materialType.isNotEmpty() && matWeight != null && matPrice != null) {
        aMaterial.materialId = materials.size.toLong()
        aMaterial.materialWeight = matWeight
        aMaterial.materialPrice = matPrice
        materials.add(aMaterial.copy())
        logger.info("Material Added : [ $aMaterial ]")
    }
    else println("Material Not Added!!!")
}

fun addPrinter() {
    var aPrinter = PrinterModel()
    println("Add Printer")
    println()
    print("Enter a Printer Name : ")
    aPrinter.printerName = readLine()!!

    println("Add a Printer Price")
    println()
    print("Enter Printer Price : ")
    aPrinter.printerPrice = readLine()?.toDouble()!!

    println("Add Printers Power Usage")
    println()
    print("Enter Printers Power Usage in Watts : ")
    aPrinter.wattUsage = readLine()?.toInt()!!

    println("Add Printers Return on Investment")
    println()
    print("In How Many Months Do You Want To Repay The Printer : ")
    aPrinter.investmentReturn = readLine()?.toInt()!!

    if(aPrinter.printerName.isNotEmpty()) {
        aPrinter.printerId = printers.size.toLong()
        printers.add(aPrinter.copy())
        logger.info("Printer Added : [ $aPrinter ]")
    }
    else println("Printer Not Added!!!")

}

fun updateMaterial() {
    println("Update Material")
    println()
    listAllMaterials()
    var searchId = getId()
    val aMaterial = searchMaterial(searchId)

    if(aMaterial != null) {
        println("Enter a new Name for [ ${aMaterial.materialName} ] : ")
        var matName : String = readLine()!!
        println("Enter a new Material Type for [ ${aMaterial.materialType} ] : ")
        var matType : String = readLine()!!
        println("Enter a new Weight for [ ${aMaterial.materialWeight} ] : ")
        var matWeight : Int = readLine()?.toInt()!!
        println("Enter a new Price for [ ${aMaterial.materialPrice} ] : ")
        var matPrice : Double = readLine()?.toDouble()!!

        if(matName.isNotEmpty() && matType.isNotEmpty()) {
            aMaterial.materialName = matName
            aMaterial.materialType = matType
            aMaterial.materialWeight = matWeight
            aMaterial.materialPrice = matPrice
            println("You Updated [ ${aMaterial.materialName} ] for Name")
            println("You Updated [ ${aMaterial.materialType} ] for Type")
            println("You Updated [ ${aMaterial.materialWeight} ] for Weight")
            println("You Updated [ ${aMaterial.materialPrice} ] for Price")
        }
        else println("Material Not Updated...")
    }
    else println("Material Not Updated...")
}

fun updatePrinter() {
    println("Update Printer")
    println()
    listAllPrinters()
    var searchId = getId()
    val aPrinter = searchPrinter(searchId)

    if(aPrinter != null) {
        println("Enter a new Name for [ ${aPrinter.printerName} ] : ")
        var prntName : String = readLine()!!
        println("Enter a new Price for [ ${aPrinter.printerPrice} ] : ")
        var prntPrice : Double = readLine()?.toDouble()!!
        println("Enter a new Watt Usage for [ ${aPrinter.wattUsage} ] : ")
        var prntWatts : Int = readLine()?.toInt()!!
        println("Enter a new Price for [ ${aPrinter.investmentReturn} ] : ")
        var prntInvestment : Int = readLine()?.toInt()!!

        if(prntName.isNotEmpty()) {
            aPrinter.printerName = prntName
            aPrinter.printerPrice = prntPrice
            aPrinter.wattUsage = prntWatts
            aPrinter.investmentReturn = prntInvestment
            println("You Updated [ ${aPrinter.printerName} ] for Name")
            println("You Updated [ ${aPrinter.printerPrice} ] for Price")
            println("You Updated [ ${aPrinter.wattUsage} ] for Watt Usage")
            println("You Updated [ ${aPrinter.investmentReturn} ] for Investment Return")
        }
        else println("Printer Not Updated...")
    }
    else println("Printer Not Updated...")
}

fun listAllMaterials() {
    println("List All Materials")
    println()
    materials.forEach {logger.info("${it}")}
    println()
}

fun listAllPrinters() {
    println("List All Printers")
    println()
    printers.forEach {logger.info("${it}")}
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

fun searchMaterial(id: Long) : MaterialModel? {
    var foundMaterial: MaterialModel? = materials.find {m -> m.materialId == id}
    return foundMaterial
}

fun searchPrinter(id: Long) : PrinterModel? {
    var foundPrinter: PrinterModel? = printers.find {p -> p.printerId == id}
    return foundPrinter
}
