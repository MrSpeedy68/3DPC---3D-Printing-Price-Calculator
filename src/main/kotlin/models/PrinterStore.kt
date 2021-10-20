package models;

import javafx.collections.ObservableList

interface PrinterStore {
    fun findAll(): List<PrinterModel>
    fun findAllObservable() : ObservableList<PrinterModel>
    fun findOne(id: Long) : PrinterModel?
    fun create(printer: PrinterModel)
    fun update(printer: PrinterModel, updatedPrinter: PrinterModel)
    fun delete(printer: PrinterModel)
    fun search(name: String) : PrinterModel?
}
