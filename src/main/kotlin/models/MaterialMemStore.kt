import mu.KotlinLogging
import models.MaterialModel
import models.MaterialStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MaterialMemStore : MaterialStore {

    val materials = ArrayList<MaterialModel>()

    override fun findAll(): List<MaterialModel> {
        return materials
    }

    override fun findOne(id: Long): MaterialModel? {
        var foundMaterial: MaterialModel? = materials.find { m -> m.materialId == id}
        return foundMaterial
    }

    override fun create(material: MaterialModel) {
        material.materialId = getId()
        materials.add(material)
        logAll()
    }

    override fun update(material: MaterialModel) {
        var foundMaterial = findOne(material.materialId!!)
        if (foundMaterial != null) {
            foundMaterial.materialName = material.materialName
            foundMaterial.materialType = material.materialType
            foundMaterial.materialWeight = material.materialWeight
            foundMaterial.materialPrice = material.materialPrice
        }
    }

    internal fun logAll() {
        materials.forEach { logger.info("${it}") }
    }
}