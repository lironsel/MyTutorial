package iamhere.ciapps.net.mytutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List <ListItem> listItems;

    private Button coolness1;
    private Button coolness2;
    private Button coolness3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        for (int i=0; i<10; i++){
            ListItem item = new ListItem("item "+ (i+1),"Description", "Excellent");
            listItems.add(item);
        }

        adapter= new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);


        coolness1 = (Button) findViewById(R.id.coolness1ID);
        coolness1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, manyFeatures.class);
                MainActivity.this.startActivity(intent);
            }
        });

        coolness2 = (Button) findViewById(R.id.coolness2ID);
        coolness2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, batActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        coolness3 = (Button) findViewById(R.id.coolness3ID);
        coolness3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MediaPlayerActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public void onItemClick(ListItem item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("rating", item.getRating());
        startActivity(intent);
    }
}
