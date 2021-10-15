package models

import javafx.collections.ObservableList

interface MaterialStore {
    fun findAll(): List<MaterialModel>
    fun findAllObservable() : ObservableList<MaterialModel>
    fun findOne(id: Long): MaterialModel?
    fun create(material: MaterialModel)
    fun update(material: MaterialModel, updatedMat: MaterialModel)
    fun delete(material: MaterialModel)
}