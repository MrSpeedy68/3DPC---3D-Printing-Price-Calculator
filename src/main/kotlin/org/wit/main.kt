package org.wit

import controllers.CalculationController
import controllers.MaterialController
import controllers.PrinterController
import controllers.UserController
import models.UserModel
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}

val user = UserModel()

val materialController = MaterialController()
val printerController = PrinterController()
val calculationController = CalculationController()
val userController = UserController()

fun main(args: Array<String>) {
    //logger.info { "Launching 3D Printing Price Calculator" }
    println("3D Printing Price Calculator App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addMaterial()
            2 -> listAllMaterials()
            3 -> updateMaterial()
            4 -> searchMaterial()
            5 -> deleteMaterial()
            6 -> addPrinter()
            7 -> listAllPrinters()
            8 -> updatePrinter()
            9 -> searchPrinter()
            10 -> deletePrinter()
            11 -> performCalculation()
            12 -> createUser()
            13 -> updateUser()
            14 -> showUser()
            -1 -> println("Exiting App")
            99 -> dummyMaterialData()
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info {"Shutting Down 3DPC Console App"}
}

fun menu() : Int {

    var option : Int
    var input : String? = null

    println("======Main Menu======")
    println("=====Materials=====")
    println(" 1. Add Material")
    println(" 2. List All Materials")
    println(" 3. Update Material")
    println(" 4. Search Material")
    println(" 5. Delete Material")
    println("=====Printers=====")
    println(" 6. Add Printer")
    println(" 7. List All Printers")
    println(" 8. Update Printer")
    println(" 9. Search Printer")
    println("10. Delete Printer")
    println("11. Perform Calculation")
    println("=====User=====")
    println("12. Create User")
    println("13. Update User")
    println("14. Show User")
    println("-1. Exit")
    println("99. Dummy Data")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun performCalculation() {
    calculationController.GetPrintInformation()
}

fun createUser() {
    userController.add()
}

fun updateUser() {
    userController.update()
}

fun showUser() {
    userController.list()
}

fun addMaterial() {
    materialController.add()
}

fun listAllMaterials() {
    materialController.list()
}

fun updateMaterial() {
    materialController.update()
}

fun searchMaterial() {
    materialController.search()
}

fun deleteMaterial() {
    materialController.delete()
}

//=======================================\\

fun addPrinter() {
    printerController.add()
}

fun listAllPrinters() {
    printerController.list()
}

fun updatePrinter() {
    printerController.update()
}

fun searchPrinter() {
    printerController.search()
}

fun deletePrinter() {
    printerController.delete()
}

fun dummyMaterialData() {
    materialController.dummyData()
    printerController.dummyData()
    userController.dummyData()
}
