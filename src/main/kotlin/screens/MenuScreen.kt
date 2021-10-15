package screens

import controllers.MenuUIController
import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import models.*
import tornadofx.*

class MenuScreen : View("3D Printing Price Calculator") {

    val menuUIController : MenuUIController by inject()
    val model = ViewModel()
    var userJSONStore = UserJSONStore()

    val _modelWeight = model.bind { SimpleIntegerProperty() }
    val _hours = model.bind { SimpleIntegerProperty() }
    val _minutes = model.bind { SimpleIntegerProperty() }

    var selectedPrinter = model.bind { SimpleObjectProperty<PrinterModel>() }
    var selectedMaterial = model.bind { SimpleObjectProperty<MaterialModel>() }

    //var price: Double = 0.0

    var total = model.bind { SimpleStringProperty("Total Price: ") }

    val printers = PrinterJSONStore()
    var printerData = printers.findAll().observable()


    val materials = MaterialJSONStore()
    var materialData = materials.findAllObservable().observable()




    override val root = form {
        setPrefSize(400.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Material Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingMaterialScreen()
                    }
                }
            }
            text("")
            button("Printer Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingPrinterScreen()
                    }
                }
            }
            text("")
            button("User Menu") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        menuUIController.loadingUserScreen()
                    }
                }
            }

            //-----------Combo Box----------------\\
            text("")
//Make a function that refreshes the list on opening the main menu
            combobox(selectedMaterial,materialData) {
            }

            text("")
//Make a function that refreshes the list on opening the main menu
            combobox(selectedPrinter, printerData)

            //===============Calculation================
            field("Model Weight") {
                textfield(_modelWeight).required()
            }
            field("Hours") {
                textfield(_hours).required()
            }
            field("Minutes") {
                textfield(_minutes).required()
            }

            text("")
            button("Calculate") {

                isDefaultButton = false
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        total.value = menuUIController.loadingCalculation(selectedMaterial.value, selectedPrinter.value, userJSONStore.find(),
                            _modelWeight.intValue(), _hours.intValue(), _minutes.intValue())

                    }
                }
            }

            text(total)


            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }
    }

    init {
        runAsync {
            printerData = printers.findAll().observable()
            materialData = materials.findAllObservable().observable()
        }
    }



}