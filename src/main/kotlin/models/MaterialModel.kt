package models

data class MaterialModel (var materialId: Long = 0,
                          var materialName: String = "",
                          var materialType: String = "",
                          var materialWeight: Int = 0,
                          var materialPrice: Double = 0.0)
{
}