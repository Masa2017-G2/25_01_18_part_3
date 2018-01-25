package com.sheygam.masa_g2_25_01_18_part_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.AdapterClickListener {
    private RecyclerView myList;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new MyAdapter(this);

        myList = findViewById(R.id.my_list);
        myList.setLayoutManager(manager);
        myList.setAdapter(adapter);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        myList.addItemDecoration(divider);

        findViewById(R.id.add_btn)
                .setOnClickListener(this);
        findViewById(R.id.remove_btn)
                .setOnClickListener(this);

        ItemTouchHelper helper = new ItemTouchHelper(new MyTochHelperCallback());
        helper.attachToRecyclerView(myList);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            adapter.addPerson();
        }else if(v.getId() == R.id.remove_btn){
            adapter.remove();
        }
    }

    @Override
    public void onRowClick(int position, Person p) {
        Toast.makeText(this, "was clicked " + p.getName(), Toast.LENGTH_SHORT).show();
    }

    class MyTochHelperCallback extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            adapter.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.remove(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }
}
