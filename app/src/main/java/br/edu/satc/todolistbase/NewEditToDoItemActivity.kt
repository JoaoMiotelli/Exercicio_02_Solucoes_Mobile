package br.edu.satc.todolistbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditToDoItemActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_to_do_item)

        initDatabase()

        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        btSave = findViewById(R.id.bt_save)

        // Click do botão salvar
        btSave.setOnClickListener {
            save()
        }
    }

    private fun save(){
        // Criar o objeto do nosso ToDoItem
        val toDoItem = ToDoItem(
            null,
            etDescription.text.toString(),
            etTitle.text.toString(),
            false
        )

        db.toDoItemDao().insertAll(toDoItem)

        Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun initDatabase() {
        // Inicializar nosso banco de dados Android Room Persistence com SQLITE
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()
    }
}