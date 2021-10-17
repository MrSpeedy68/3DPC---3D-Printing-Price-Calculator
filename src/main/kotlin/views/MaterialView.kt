package views

import models.MaterialModel
import models.MaterialJSONStore

class MaterialView {
    //Check if all material data is valid before adding
    fun addMaterialData(material : MaterialModel) : Boolean {
        if(material.materialName.isEmpty()) {
            return false
        }
        if(material.materialType.isEmpty()) {
            return false
        }
        if(material.materialWeight <= 0) {
            return false
        }
        if(material.materialPrice <= 0) {
            return false
        }

        return true
    }

    //Check if all updated material data is valid before adding
    fun updateMaterialData(material : MaterialModel) : Boolean {
        println("Update Material")
        println()

        var tempMatName: String?
        var tempMatType: String?
        var tempMatWeight: Int?
        var tempMatPrice: Float?

        if(material != null) {
            println("Enter a new Name for [ ${material.materialName} ] : ")
            tempMatName  = readLine()!!
            println("Enter a new Material Type for [ ${material.materialType} ] : ")
            tempMatType = readLine()!!
            println("Enter a new Weight for [ ${material.materialWeight} ] : ")
            tempMatWeight = readLine()?.toInt()!!
            println("Enter a new Price for [ ${material.materialPrice} ] : ")
            tempMatPrice= readLine()?.toFloat()!!

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

}