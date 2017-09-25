package net.agusharyanto.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClicked
{

    private EditText editTextTask;
    private Button buttonSimpan;
    private RecyclerView recyclerViewTask;
    private TaskAdapter taskAdapter;

    private ArrayList<String> listtask = new ArrayList<String>();
    private String activeTask="";
    private int activePosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }



    private void findViews() {
        editTextTask = (EditText)findViewById( R.id.editTextTask );
        buttonSimpan = (Button)findViewById( R.id.buttonSimpan );
        recyclerViewTask = (RecyclerView)findViewById( R.id.recyclerView_task );

        recyclerViewTask.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewTask.setLayoutManager(layoutManager);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        taskAdapter = new TaskAdapter(listtask);
        taskAdapter.setOnClick(this);
        recyclerViewTask.setAdapter(taskAdapter);

    }

    private void addData() {
        listtask.add(editTextTask.getText().toString());
        taskAdapter.notifyDataSetChanged();
        editTextTask.setText("");
    }


    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(getBaseContext(), position+". "+listtask.get(position), Toast.LENGTH_SHORT).show();
        activeTask =listtask.get(position);
        activePosition = position;
        PopupMenu popup = new PopupMenu(this, view);
        //inflating menu from xml resource
        popup.inflate(R.menu.menu_task);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        editTask();
                        break;
                    case R.id.menu2:
                        //handle menu2 click
                        break;
                    case R.id.menu3:
                        //handle menu3 click
                        break;
                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }


    private void editTask(){
        editTextTask.setText(activeTask);
    }

}
