package com.utn.primerparcial.login.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.primerparcial.R
import com.utn.primerparcial.login.models.User
import com.utn.primerparcial.movies.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingUsers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Pre-populating database...")
            fillWithStartingUsers(context)
        }
    }


    private fun fillWithStartingUsers(context: Context) {
        val users = listOf(
            User(1,"Martin Destefano", "martin@gmail.com", "2222", 23, "The Godfather"),
            User(2,"Franco Torres", "franco@gmail.com", "2222", 18, "Mamma Mia")
        )
        val dao = AppDatabase.getInstance(context)?.userDao()

        users.forEach {
            dao?.insertUser(it)
        }
    }

    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.userDao()

        try {
            val users = loadJSONArray(context, R.raw.users)
            for (i in 0 until users.length()) {
                val item = users.getJSONObject(i)
                val user = User(
                    id = item.getInt("id"),
                    name = item.getString("name"),
                    email = item.getString("email"),
                    password = item.getString("password"),
                    age = item.getInt("age"),
                    favMovie = item.getString("favMovie")
                )

                dao?.insertUser(user)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}