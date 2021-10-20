package models

import javafx.collections.ObservableList

interface MaterialStore {
    fun findAll(): MutableList<MaterialModel>
    fun size(): Int
    fun findAllObservable() : ObservableList<MaterialModel>
    fun findOne(id: Long): MaterialModel?
    fun create(material: MaterialModel)
    fun update(material: MaterialModel, updatedMat: MaterialModel)
    fun delete(material: MaterialModel)
    fun search(name: String) : MaterialModel?
}