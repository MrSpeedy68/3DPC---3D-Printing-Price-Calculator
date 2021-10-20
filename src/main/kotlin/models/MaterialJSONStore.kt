package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import views.MaterialView
import java.util.*
import kotlin.collections.ArrayList

private val logger = KotlinLogging.logger {}

val JSON_FILE_MATERIAL = "materials.json"
val gsonBuilder_Mat = GsonBuilder().setPrettyPrinting().create()
val listTypeMat = object : TypeToken<ArrayList<MaterialModel>>() {}.type

val materialView = MaterialView()

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


    override fun size(): Int {
        return materials.size - 1
    }

    override fun findAllObservable() : ObservableList<MaterialModel> {
        val obsList = FXCollections.observableList(materials)

        return obsList
    }

    override fun findOne(id: Long) : MaterialModel? {
        val foundMaterial: MaterialModel? = materials.find { m -> m.materialId == id }
        return foundMaterial
    }

    override fun create(material: MaterialModel)  {
        material.materialId = generateRandomIdMaterial()
        if(materialView.addMaterialData(material)) {
            materials.add(material)
            serialize()
            logger.info ("Material Added!!!")
        }
        else
            logger.info ("Material Not Added!!!")

    }

    override fun update(material: MaterialModel, updatedMat: MaterialModel) {
        println("this mat $material")
        println("updated mat $updatedMat")

        material.materialName = updatedMat.materialName
        material.materialType = updatedMat.materialType
        material.materialWeight = updatedMat.materialWeight
        material.materialPrice = updatedMat.materialPrice

        serialize()

        logger.info ("Material Updated!!!")
    }

    override fun delete(material: MaterialModel) {
        materials.remove(material)
        serialize()
        logger.info("Material Deleted!!!")
    }

    override fun search(name: String) : MaterialModel? {
        val foundMaterial: MaterialModel? = materials.find { n -> n.materialName.uppercase() == name.uppercase() }
        return foundMaterial
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