package views

import models.UserMemStore
import models.UserModel

class UserView {

    fun showUser(user: UserModel) {
        if(user != null)
            println("User Details [ $user ]")
        else
            println("User Not Found...")
    }

    fun addUserData(user: UserModel) : Boolean {
        print("Enter a User Name : ")
        user.userName = readLine()!!

        print("Enter how much you your time costs per hour : ")
        user.labourCost = readLine()?.toDouble()!!

        print("Enter how much 1 Kwh of electricity costs : ")
        user.energyCost = readLine()?.toDouble()!!

        print("Enter your preferred currency : ")
        user.currency = readLine()!!

        return user.userName.isNotEmpty()
    }

    fun updateUserData(user: UserModel) : Boolean {
        println("Update User")
        println()

        var tempUserName: String?
        var tempLabourCost: Double?
        var tempEnergyCost: Double?
        var tempCurrency: String?

        if(user!=null) {
            println("Enter a new User Name for [ ${user.userName} ] : ")
            tempUserName = readLine()!!

            println("Enter a new labour cost for [ ${user.labourCost} ] : ")
            tempLabourCost = readLine()?.toDouble()!!

            println("Enter a new cost for 1 Kwh of electricity for [ ${user.energyCost} ] : ")
            tempEnergyCost = readLine()?.toDouble()!!

            println("Enter a new currency for [ ${user.currency} ] : ")
            tempCurrency = readLine()!!

            if(tempUserName.isNullOrEmpty() && tempCurrency.isNullOrEmpty()) {
                user.userName = tempUserName
                user.labourCost = tempLabourCost
                user.energyCost = tempEnergyCost
                user.currency = tempCurrency
                return true
            }
        }
        return false
    }
}