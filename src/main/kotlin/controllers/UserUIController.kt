package controllers

import models.UserJSONStore
import models.UserModel
import mu.KotlinLogging
import screens.MenuScreen
import screens.UserScreen
import tornadofx.*

class UserUIController : Controller() {

    var mainUser = UserJSONStore()
    var logger = KotlinLogging.logger {}

    fun add(_name : String, _labour : Double, _energy : Double, _currency : String){

        var aUser = UserModel(userName = _name, labourCost = _labour, energyCost = _energy, currency = _currency)
        mainUser.create(aUser)
        logger.info("User Added")
    }

    fun closeUser() {
        runLater {
            find(UserScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}