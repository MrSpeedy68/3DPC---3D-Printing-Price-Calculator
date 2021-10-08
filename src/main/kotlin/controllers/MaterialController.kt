package controllers

import MaterialMemStore
import models.MaterialModel
import mu.KotlinLogging
import org.wit.searchMaterial
import views.MaterialView

class MaterialController {

    val materials = MaterialMemStore()
    val materialView = MaterialView()
    val logger = KotlinLogging.logger {}


    fun add() {
        var aMaterial = MaterialModel()

        if(materialView.addMaterialData(aMaterial))
            materials.create(aMaterial)
        else
            logger.info("Material Not Added")
    }

    fun list() {
        materialView.listAllMaterials(materials)
    }

    fun update() {
        materialView.listAllMaterials(materials)
        var searchId = materialView.getId()
        val aMaterial = search(searchId)

        if(aMaterial != null) {
            if(materialView.updateMaterialData(aMaterial)) {
                materials.update(aMaterial)
                materialView.showMaterial(aMaterial)
                logger.info("Material Updated : [ $aMaterial ]")
            }
            else
                logger.info("Material Not Updated...")
        }
        else
            logger.info("Material Not Updated...")
    }

    fun search() {
        val aMaterial = search(materialView.getId())!!
        materialView.showMaterial(aMaterial)
    }

    fun search(id: Long) : MaterialModel? {
        var foundMaterial = materials.findOne(id)
        return foundMaterial
    }

    fun dummyData() {
        materials.create(MaterialModel(materialName = "Esun Blue", materialType = "PLA", materialWeight = 1000, materialPrice = 26.50))
        materials.create(MaterialModel(materialName = "Esun Purple", materialType = "ABS", materialWeight = 1000, materialPrice = 29.50))
        materials.create(MaterialModel(materialName = "GEETECH Grey", materialType = "PLA", materialWeight = 1000, materialPrice = 16.00))
    }
}