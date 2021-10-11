package models

interface MaterialStore {
    fun findAll(): List<MaterialModel>
    fun findOne(id: Long): MaterialModel?
    fun create(material: MaterialModel)
    fun update(material: MaterialModel)
    fun delete(material: MaterialModel)
}