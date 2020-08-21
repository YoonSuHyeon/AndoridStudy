package com.example.exampleprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import java.lang.UnsupportedOperationException

class MyContentProvider : ContentProvider() {
    private lateinit var db: SQLiteDatabase



    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return db.delete("tb_data", selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert("tb_data", null, values)
        return null
    }

    override fun onCreate(): Boolean {
        val helper = DBHelper(context)
        db = helper.writableDatabase
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return db.query("tb_data",projection,selection,selectionArgs,null,null,sortOrder)
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return db.update("tb_data",values,selection,selectionArgs)
    }
}
