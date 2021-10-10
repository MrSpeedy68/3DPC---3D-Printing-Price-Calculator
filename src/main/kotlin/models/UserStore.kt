package models

interface UserStore {
    fun create(user: UserModel)
    fun update(user: UserStore)
}