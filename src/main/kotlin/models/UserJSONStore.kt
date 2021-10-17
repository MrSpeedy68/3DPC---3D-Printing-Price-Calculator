package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.placemark.console.helpers.exists
import org.wit.placemark.console.helpers.read
import org.wit.placemark.console.helpers.write

private val logger = KotlinLogging.logger {}

val JSON_FILE_USER = "user.json"
val gsonBuilder_User = GsonBuilder().setPrettyPrinting().create()
val listType_User = object : TypeToken<UserModel>() {}.type

class UserJSONStore : UserStore{
    var mainUser = UserModel()

    init {
        if (exists(JSON_FILE_USER)) {
            deserialize()
        }
    }

    //There is only a single user so the main user is returned
    override fun find() : UserModel {
        return mainUser
    }

    override fun create(user: UserModel) {
        mainUser = user
        serialize()
    }

    override fun update(user: UserModel) {
        mainUser.userName = user.userName
        mainUser.currency = user.currency
        mainUser.energyCost = user.energyCost
        mainUser.labourCost = user.labourCost

        serialize()
    }

    internal fun log() {
        logger.info {"$mainUser"}
    }

    private fun serialize() {
        val jsonString = gsonBuilder_User.toJson(mainUser, listType_User)
        write(JSON_FILE_USER, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_USER)
        mainUser = Gson().fromJson(jsonString, listType_User)
    }
}