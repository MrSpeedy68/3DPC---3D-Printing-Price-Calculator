package models

data class UserModel(var labourCost: Double = 0.0,
                     var energyCost: Double = 0.0,
                     var currency: String = "$")
{
}