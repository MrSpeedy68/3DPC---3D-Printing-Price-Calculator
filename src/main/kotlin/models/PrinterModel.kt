package models

data class PrinterModel(var printerId: Long = 0,
                        var printerName: String = "",
                        var printerPrice: Float = 0.0f,
                        var wattUsage: Int = 0,
                        var investmentReturn: Int = 0)
{
}