package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.content.Context
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException


class BaseDeDatosTest {
    //private lateinit var tareas: ArrayList<String>
    //private lateinit var db: AdminBD

   /* @RunWith(AndroidJUnit4::class)
    class SimpleEntityReadWriteTest {
        private lateinit var tareas: Tarea
        private lateinit var db: AdminBD

        @Before
        fun createDb() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, TestDatabase::class.java).build()
            userDao = db.getUserDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun writeUserAndReadInList() {
            val user: User = TestUtil.createUser(3).apply {
                setName("george")
            }
            userDao.insert(user)
            val byName = userDao.findUsersByName("george")
            assertThat(byName.get(0), equalTo(user))
        }
    }*/

}