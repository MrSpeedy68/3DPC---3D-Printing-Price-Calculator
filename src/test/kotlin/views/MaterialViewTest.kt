package views

import models.MaterialModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MaterialViewTest {

    val materialView = MaterialView()


    @Test
    fun `Correct Data Gets Added`() {
        assertEquals(true,materialView.addMaterialData(MaterialModel(
            materialName = "Esun Blue",
            materialType = "PLA",
            materialWeight = 1000,
            materialPrice = 26.50f)
        ))
    }

    @Test
    fun `Name is Empty`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "",
            materialType = "PLA",
            materialWeight = 1000,
            materialPrice = 26.50f)
        ))
    }

    @Test
    fun `Type is Empty`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "Blue",
            materialType = "",
            materialWeight = 1000,
            materialPrice = 26.50f)
        ))
    }

    @Test
    fun `Weight is Negative`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "Blue",
            materialType = "PLA",
            materialWeight = -100,
            materialPrice = 26.50f)
        ))
    }

    @Test
    fun `Weight is 0`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "Blue",
            materialType = "PLA",
            materialWeight = 0,
            materialPrice = 26.50f)
        ))
    }

    @Test
    fun `Price is Negative`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "Blue",
            materialType = "PLA",
            materialWeight = 1000,
            materialPrice = -26.50f)
        ))
    }

    @Test
    fun `Price is 0`() {
        assertEquals(false,materialView.addMaterialData(MaterialModel(
            materialName = "Blue",
            materialType = "PLA",
            materialWeight = 1000,
            materialPrice = 0f)
        ))
    }


    @Test
    fun updateMaterialData() {
    }
}