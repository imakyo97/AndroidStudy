package com.example.menusample

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    private val _from = arrayOf("name", "price")
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        _menuList = createTeishokuList()
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()

        registerForContextMenu(lvMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        when(item.itemId) {
            R.id.menuListOptionTeishoku -> _menuList = createTeishokuList()
            R.id.menuListOptionCurry -> _menuList = createCurryList()
            else -> returnVal = super.onOptionsItemSelected(item)
        }
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter
        return returnVal
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderTitle(R.string.menu_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList[listPosition]

        when(item.itemId) {
            R.id.menuListContextOrder -> order(menu)
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(this@MainActivity, desc, Toast.LENGTH_LONG).show()
            }
            else -> returnVal = super.onContextItemSelected(item)
        }
        return returnVal
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食", "price" to 800, "desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 900, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 600, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1100, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 600, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 800, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "メンチカツ定食", "price" to 800, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 600, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "回鍋肉定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "麻婆豆腐", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "青椒肉絲定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "八宝菜定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "酢豚定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "豚の角煮定食", "price" to 900, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き鳥定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 700, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to 900, "desc" to "サラダ、ご飯とお味噌汁が付きます")
        menuList.add(menu)
        return menuList
    }

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー", "price" to 500, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "ポークカレー", "price" to 420, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "ハンバーグカレー", "price" to 620, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "チーズカレー", "price" to 560, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "カツカレー", "price" to 760, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "ビーフカレー", "price" to 880, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "から揚げカレー", "price" to 540, "desc" to "特選スパイスをきかせたカレーです")
        menuList.add(menu)
        return menuList
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }

    }

    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int
        val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
        intent2MenuThanks.putExtra("menuName", menuName)
        intent2MenuThanks.putExtra("menuPrice", "${menuPrice}円")
        startActivity(intent2MenuThanks)
    }
}
