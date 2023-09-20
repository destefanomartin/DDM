package com.utn.primerparcial.login.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.utn.primerparcial.login.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY name")
    fun fetchAllUsers(): MutableList<User?>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun fetchUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): MutableList<User?>?
    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}