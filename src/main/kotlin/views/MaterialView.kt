package views

import models.MaterialModel
import models.MaterialJSONStore

class MaterialView {

    fun showMaterial(material: MaterialModel) {
        if(material != null)
            println("Material Details [ $material ]")
        else
            println("Material Not Found...")
    }

    fun addMaterialData(material : MaterialModel) : Boolean {
        print("Enter a Material Name : ")
        material.materialName = readLine()!!

        print("Enter a Material Type : ")
        material.materialType = readLine()!!

        print("Enter Material Weight : ")
        material.materialWeight = readLine()?.toInt()!!

        print("Enter a Material Price : ")
        material.materialPrice = readLine()?.toDouble()!!

        return material.materialName.isNotEmpty() && material.materialType.isNotEmpty()
    }

    fun updateMaterialData(material : MaterialModel) : Boolean {
        println("Update Material")
        println()

        var tempMatName: String?
        var tempMatType: String?
        var tempMatWeight: Int?
        var tempMatPrice: Double?

        if(material != null) {
            println("Enter a new Name for [ ${material.materialName} ] : ")
            tempMatName  = readLine()!!
            println("Enter a new Material Type for [ ${material.materialType} ] : ")
            tempMatType = readLine()!!
            println("Enter a new Weight for [ ${material.materialWeight} ] : ")
            tempMatWeight = readLine()?.toInt()!!
            println("Enter a new Price for [ ${material.materialPrice} ] : ")
            tempMatPrice= readLine()?.toDouble()!!

            if(tempMatName.isNotEmpty() && tempMatType.isNotEmpty()) {
                material.materialName = tempMatName
                material.materialType = tempMatType
                material.materialWeight = tempMatWeight
                material.materialPrice = tempMatPrice
                return true
            }
        }
        return false
    }

    fun listAllMaterials(materials : MaterialJSONStore) {
        println("List All Materials")
        println()
        materials.logAll()
        println()
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted Id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}