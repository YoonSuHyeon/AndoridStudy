package com.example.menuexample


import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView =
            findViewById<ImageView>(R.id.imageView)
        registerForContextMenu(imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_lab2, menu)
        try{
            val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible",Boolean.javaClass)
            method.isAccessible=true
            method.invoke(menu,true)
        }catch(e:Exception){
            e.printStackTrace()
        }

        val menuItem: MenuItem = menu.findItem(R.id.menu_main_search)
        searchView = menuItem.actionView as SearchView?
        searchView!!.queryHint = resources.getString(R.string.query_hint)
        searchView!!.setOnQueryTextListener(queryTextListener)
        return true
    }

    private var queryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView!!.setQuery("", false)
                searchView!!.isIconified = true
                showToast(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, 0, 0, "서버전송")
        menu.add(0, 1, 0, "보관함에 보관")
        menu.add(0, 2, 0, "삭제")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> showToast("서버 전송이 선택되었습니다.")
            1 -> showToast("보관함에 보관이 선택되었습니다.")
            2 -> showToast("삭제가 선택되었습니다.")
        }
        return true
    }

    private fun showToast(message: String) {
        val t = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        t.show()
    }
}
