package org.wit

import controllers.MaterialController
import controllers.PrinterController
import models.UserModel
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}

val user = UserModel()

val materialController = MaterialController()
val printerController = PrinterController()

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
            5 -> addPrinter()
            6 -> listAllPrinters()
            7 -> updatePrinter()
            8 -> searchPrinter()
            9 -> performCalculation()
            10 -> createUser()
            11 -> updateUser()
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

    println("===Main Menu===")
    println(" 1. Add Material")
    println(" 2. List All Materials")
    println(" 3. Update Material")
    println(" 4. Search Material")
    println(" 5. Add Printer")
    println(" 6. List All Printers")
    println(" 7. Update Printer")
    println(" 8. Search Printer")
    println(" 9. Perform Calculation")
    println("10. Create User")
    println("11. Update User")
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

}

fun createUser() {

}

fun updateUser() {

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

fun dummyMaterialData() {
    materialController.dummyData()
    printerController.dummyData()

    UserModel("Adrian",15.50,0.14,"$")
}
