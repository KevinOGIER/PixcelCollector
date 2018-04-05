package com.example.pixcelcolector.pixcelcollector.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scores.*

val DATABASE_NAME = "PixcelCollect_DB"
val TABLE_NAME = "ScoresTable"
val COL_ID = "ID"
val COL_POINT = "Score"
val COL_USERNAME = "Username"

class DataBaseHandler (private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_POINT + " INTEGER," +
                COL_USERNAME + " VARCHAR(5), "
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(score: Score)
    {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_POINT, score.point)
        contentValues.put(COL_USERNAME, score.username)
        var result  = db.insert(TABLE_NAME, null ,contentValues)
        if(result == -1.toLong())
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
        }
    }

    fun selectScoreList(): MutableList<Score> {
        var listScore: MutableList<Score> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME + " order by " + COL_POINT +  " desc limit 3"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var score = Score()
                score.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                score.username = result.getString(result.getColumnIndex(COL_USERNAME))
                score.point = result.getString(result.getColumnIndex(COL_POINT)).toInt()
                listScore.add(score)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return listScore
    }

    fun selectScoreListByUsername(username:String): MutableList<Score>
    {
        var listScoreUser: MutableList<Score> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME + " where " + COL_USERNAME + " LIKE '" + username + "'"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst())
        {
            do
            {
                var score = Score()
                score.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                score.username = result.getString(result.getColumnIndex(COL_USERNAME))
                score.point = result.getString(result.getColumnIndex(COL_POINT)).toInt()
                listScoreUser.add(score)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return listScoreUser
    }

    fun getFirstScore() : MutableList<Score>
    {
        var listBestScore:MutableList<Score> = ArrayList()
        var listScore:MutableList<Score> = ArrayList()
        val db = this.readableDatabase
        val query = "Select " + COL_USERNAME + ", " + COL_POINT + " From " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if(result.moveToFirst())
        {
            do
            {
                var score = Score()
                score.username = result.getString(result.getColumnIndex(COL_USERNAME))
                score.point = result.getString(result.getColumnIndex(COL_POINT)).toInt()
                listScore.add(score)
            } while (result.moveToNext())

            listScore.sortByDescending { it.point }

            var index = 0
            while (index < 3)
            {
                listBestScore.add(listScore.get(index))
                index ++
            }
        }
        result.close()
        db.close()
        return listBestScore
    }

    // TEMP : TEST
    fun getOneRow(): String
    {
        val db = this.readableDatabase
        val query = "Select " + COL_USERNAME + ", " + COL_POINT + " From " + TABLE_NAME
        val result = db.rawQuery(query,null)
        var score = Score()
        var username:String = ""

        if(result.moveToFirst())
        {
            username = result.getString(result.getColumnIndex(COL_USERNAME))
            score.username = result.getString(result.getColumnIndex(COL_USERNAME))
            score.point = result.getString(result.getColumnIndex(COL_POINT)).toInt()
        }

        result.close()
        db.close()

        return username
    }

    fun getClassement(username: String): String
    {
        var listScoreUser: MutableList<Score> = ArrayList()
        val db = this.readableDatabase
        val query = "select * from ScoresTable order by Score desc"
        var rank = ""

        val result = db.rawQuery(query,null)

        if(result.moveToFirst())
        {
            do
            {
                var score = Score()
                score.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                score.username = result.getString(result.getColumnIndex(COL_USERNAME))
                score.point = result.getString(result.getColumnIndex(COL_POINT)).toInt()
                listScoreUser.add(score)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        var topScoreList: MutableList<Score> = listScoreUser

        var iterator = topScoreList.listIterator()

        var i = 0

        for(monScore in iterator)
        {
            i++
            if(monScore.username == username){
                rank = i.toString()
                break
            }
        }

        return rank
    }
}