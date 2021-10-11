package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import models.MaterialModel
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE_MATERIAL = "materials.json"
val gsonBuilder_Mat = GsonBuilder().setPrettyPrinting().create()
val listTypeMat = object : TypeToken<java.util.ArrayList<MaterialModel>>() {}.type

fun generateRandomIdMaterial(): Long {
    return Random().nextLong()
}

class MaterialJSONStore : MaterialStore {

    var materials = mutableListOf<MaterialModel>()

    init {
        if (exists(JSON_FILE_MATERIAL)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MaterialModel> {
        return materials
    }

    override fun findOne(id: Long) : MaterialModel? {
        var foundMaterial: MaterialModel? = materials.find { m -> m.materialId == id }
        return foundMaterial
    }

    override fun create(material: MaterialModel) {
        material.materialId = generateRandomIdMaterial()
        materials.add(material)
        serialize()
    }

    override fun update(material: MaterialModel) {
        var foundMaterial = findOne(material.materialId!!)
        if (foundMaterial != null) {
            foundMaterial.materialName = material.materialName
            foundMaterial.materialType = material.materialType
            foundMaterial.materialWeight = material.materialWeight
            foundMaterial.materialPrice = material.materialPrice
        }
        serialize()
    }

    internal fun logAll() {
        materials.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder_Mat.toJson(materials, listTypeMat)
        write(JSON_FILE_MATERIAL, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_MATERIAL)
        materials = Gson().fromJson(jsonString, listTypeMat)
    }
}