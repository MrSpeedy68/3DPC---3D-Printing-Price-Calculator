package models;

interface PrinterStore {
    fun findAll(): List<PrinterModel>
    fun findOne(id: Long) : PrinterModel?
    fun create(printer: PrinterModel)
    fun update(printer: PrinterModel)
    fun delete(printer: PrinterModel)
}
