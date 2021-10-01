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
    aMaterial.materialWeight = readLine()?.toInt()!!

    println("Add a Material Price")
    println()
    print("Enter a Material Price : ")
    aMaterial.materialPrice = readLine()?.toDouble()!!

    if(aMaterial.materialName.isNotEmpty() && aMaterial.materialType.isNotEmpty()) {
        aMaterial.materialId = materials.size.toLong()
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
