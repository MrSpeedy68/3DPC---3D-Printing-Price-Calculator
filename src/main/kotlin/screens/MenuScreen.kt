package screens

import controllers.MenuUIController
import javafx.application.Platform
import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.Font
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

    var totalMaterial = model.bind { SimpleStringProperty("Total Material Cost: ") }
    var totalEnergy = model.bind { SimpleStringProperty("Total Energy Cost: ") }
    var totalPrinter = model.bind { SimpleStringProperty("Total Printer Cost: ") }
    var totalPrice = model.bind { SimpleStringProperty("Total Printing Cost: ") }

    var printerData = menuUIController.printerData
    var materialData = menuUIController.materialData


    override val root = form {

        setPrefSize(400.0, 700.0)
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
            text("Select Material")
            text("")

////Make a function that refreshes the list on opening the main menu
            combobox(selectedMaterial,materialData) {
            }

            text("")
            text("Select Printer")
            text("")

//Make a function that refreshes the list on opening the main menu
            combobox(selectedPrinter, printerData) {
            }

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
                        totalPrice.value = menuUIController.loadingTotalCost(selectedMaterial.value, selectedPrinter.value, userJSONStore.find(),
                            _modelWeight.intValue(), _hours.intValue(), _minutes.intValue())

                        totalMaterial.value = menuUIController.loadingTotalMaterialCost(selectedMaterial.value,_modelWeight.intValue(), userJSONStore.find())

                        totalEnergy.value = menuUIController.loadingTotalEnergyCost(selectedPrinter.value,_hours.intValue(),_minutes.intValue(), userJSONStore.find())

                        totalPrinter.value = menuUIController.loadingTotalPrinterCost(selectedPrinter.value, _hours.intValue(), _minutes.intValue(), userJSONStore.find())
                    }
                }
            }

            text("")
            text("")
            text("")

            text(totalMaterial) {
                font = Font(15.0)
            }
            text(totalEnergy) {
                font = Font(15.0)
            }
            text(totalPrinter) {
                font = Font(15.0)
            }
            text(totalPrice) {
                font = Font(15.0)
            }

            text("")
            text("")
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
                style {
                    backgroundColor += Color.INDIANRED
                }
            }
        }
    }
}