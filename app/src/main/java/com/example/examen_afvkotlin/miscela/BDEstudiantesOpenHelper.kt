package com.example.examen_afvkotlin.miscela

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen_afvkotlin.Objetos.Estudiante

class BDEstudiantesOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, name, null, version)  {

    companion object {
        private const val  version = 1
        private const val name = "centro.db"
        private const val TBL_ALUMNO = "alumnos"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val APELLIDOS = "appellidos"
        private const val MEDIA = "media"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblAlumnos = ("CREATE TABLE " + TBL_ALUMNO  + "("
                + ID + " INT PRIMARY KEY, " + NOMBRE + " TEXT, " + APELLIDOS + " TEXT, " + MEDIA + " FLOAT " + ")")
        db?.execSQL(createTblAlumnos)    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, i: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_ALUMNO")
    }

    fun insertAlumno(estudiante : Estudiante) : Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, estudiante.id)
        contentValues.put(NOMBRE, estudiante.nombre)
        contentValues.put(APELLIDOS, estudiante.apellidos)
        contentValues.put(MEDIA, estudiante.media)

        val sucess = db.insert(TBL_ALUMNO, null, contentValues)
        db.close()
        return sucess
    }
    fun getAllAlumnos() : ArrayList<Estudiante>{

        val alumnList : ArrayList<Estudiante> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_ALUMNO"
        val db = this.readableDatabase

        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e : Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return  ArrayList()
        }
        var id : Int
        var nombre : String
        var apellidos : String
        var media : Float

        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0)
                nombre = cursor.getString(1)
                apellidos = cursor.getString(2)
                media = cursor.getFloat(3)

                val alm = Estudiante(id = id, nombre = nombre, apellidos = apellidos, media = media)
                alumnList.add(alm)
            }while (cursor.moveToNext())
        }
        return  alumnList
    }
    fun getAllumno(id : Int):ArrayList<Estudiante> {
        val selectQuery = "SELECT * FROM $TBL_ALUMNO WHERE id = $id"
        val alumnList : ArrayList<Estudiante> = ArrayList()
        val db = this.readableDatabase

        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e : Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return  ArrayList()
        }
        var id : Int
        var nombre : String
        var apellidos : String
        var media : Float
        if (cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(0)
                    nombre = cursor.getString(1)
                    apellidos = cursor.getString(2)
                    media = cursor.getFloat(3)

                     val alm = Estudiante(id = id, nombre = nombre, apellidos = apellidos, media = media)
                    alumnList.add(alm)
                }while (cursor.moveToNext())
            }
        }
        return alumnList
    }
    fun updateAlumno(alum : Estudiante): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, alum.id)
        contentValues.put(NOMBRE, alum.nombre)
        contentValues.put(APELLIDOS, alum.apellidos)
        contentValues.put(MEDIA, alum.media)

        val success = db.update(TBL_ALUMNO, contentValues, "id=" + alum.id, null)
        db.close()
        return  success
    }
    fun deleteAlumno(id : Int): Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_ALUMNO, "id=$id", null)
        db.close()
        return success
    }
}