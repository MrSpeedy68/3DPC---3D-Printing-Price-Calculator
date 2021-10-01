package org.wit

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

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

fun addMaterial(){
    println("Nice Material")
}

fun addPrinter() {
    println("Nice Printer")
}

fun listAllMaterials() {
    println("Here is all your materials")
}

fun listAllPrinters() {
    println("Here is all your printers")
}
