package views

import models.PrinterModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrinterViewTest {

    val printerView = PrinterView()

    @Test
    fun `Correct Data Gets Added`() {
        assertEquals(true,printerView.addPrinterData(
            PrinterModel(
               printerName = "Ender 3",
               printerPrice = 150f,
               wattUsage = 100,
               investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Name Is Empty`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 150f,
                wattUsage = 100,
                investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Price Is Negative`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = -150f,
                wattUsage = 100,
                investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Price Is 0`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 0f,
                wattUsage = 100,
                investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Watt Usage Is Negative`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 150f,
                wattUsage = -100,
                investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Watt Usage Is 0`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 150f,
                wattUsage = 0,
                investmentReturn = 24
            )
        ))
    }

    @Test
    fun `Printer Investment Is Negative`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 150f,
                wattUsage = 100,
                investmentReturn = -24
            )
        ))
    }

    @Test
    fun `Printer Investment Is 0`() {
        assertEquals(false,printerView.addPrinterData(
            PrinterModel(
                printerName = "",
                printerPrice = 150f,
                wattUsage = 100,
                investmentReturn = 0
            )
        ))
    }

    @Test
    fun addPrinterData() {
    }

    @Test
    fun updatePrinterData() {
    }
}