package controllers

import models.UserModel
import models.UserJSONStore
import mu.KotlinLogging
import views.UserView

class UserController {

    val mainUser = UserJSONStore()
    val userView = UserView()
    val logger = KotlinLogging.logger {}

    fun add() {
        var aUser = UserModel()

        if(userView.addUserData(aUser)) {
            mainUser.create(aUser)
        }
        else
            logger.info("User Not Added!!")
    }

    fun list() {
        userView.showUser(mainUser)
    }

    fun update() {
        userView.showUser(mainUser)
        val aUser = mainUser.find()

        if(aUser != null) {
            if(userView.updateUserData(aUser)) {
                mainUser.update(aUser)
                userView.showUser(mainUser)
                logger.info("User Updated : [ $aUser ]")
            }
            else
                logger.info("User Not Updated...")
        }
        else
            logger.info("User Not Updated...")
    }

    fun returnUser() : UserModel {
        return mainUser.find()
    }

    fun dummyData() {
        mainUser.create(UserModel(userName = "Adrian", labourCost = 15.0, energyCost = 0.14, currency = "€"))
    }


}