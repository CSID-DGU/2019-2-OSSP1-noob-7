package DGU.OSSP.fall2019.PersonalTrainer.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import DGU.OSSP.fall2019.PersonalTrainer.Fragments.SearchFragment;
import DGU.OSSP.fall2019.DGU.R;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {

    private List<String> meals;
    private List<String> picUrls;
    private List<String> bookmarkURLs;
    private ItemClickListener clickListener;
    SearchFragment sourceFragment;
    Context mContext;

    public SearchRecyclerViewAdapter(Context context, List<String> meals, List<String> picUrls, List<String> bookmarkURLs,
                                                        SearchFragment sourceFragment) {
        this.meals = meals;
        this.picUrls = picUrls;
        this.mContext = context;
        this.sourceFragment = sourceFragment;
        this.bookmarkURLs = bookmarkURLs;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        // create and attach ClickListener for both image and frame of Search Item
        vHolder.imView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vHolder.getAdapterPosition();
                sourceFragment.showMealDetail(bookmarkURLs.get(position));
            }
        });
        vHolder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vHolder.getAdapterPosition();
                sourceFragment.showMealDetail(bookmarkURLs.get(position));
            }
        });

        // create and attach ClickListener for Add to ShoppingList, Favorites
        vHolder.addToShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vHolder.getAdapterPosition();
                String mealBookmark = bookmarkURLs.get(position);

                sourceFragment.sendMealToShoppingList(mealBookmark, meals.get(position));


                vHolder.tvAddShopping.setText("                          ");
                vHolder.addToShoppingList.setImageResource(R.drawable.ic_done);

            }
        });
        vHolder.addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vHolder.getAdapterPosition();
                String mealBookmark = bookmarkURLs.get(position);

                SQLiteUserManager myDB = new SQLiteUserManager(mContext);
                myDB.addToFavorites(mealBookmark);

                vHolder.tvAddFavorite.setText("                          ");
                vHolder.addToFavorites.setImageResource(R.drawable.ic_done);

            }
        });
        return vHolder;
    }

    // Binds data to recycled views
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemName.setText(meals.get(position));
        new ImageLoaderFromUrl(holder.imView).execute(picUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    // Stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imView;
        private TextView itemName;
        private TableLayout searchLayout;
        private ImageButton addToShoppingList, addToFavorites;
        private TextView tvAddShopping, tvAddFavorite;

        public MyViewHolder(View itemView) {
            super(itemView);

            imView = itemView.findViewById(R.id.searchMealPic);
            itemName = itemView.findViewById(R.id.searchMealname);
            searchLayout = itemView.findViewById(R.id.searchTableLayout);
            addToShoppingList = itemView.findViewById(R.id.buttonAddShopping);
            addToFavorites = itemView.findViewById(R.id.buttonAddFavorite);
            tvAddShopping = itemView.findViewById(R.id.addText);
            tvAddFavorite = itemView.findViewById(R.id.addFavoriteText);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition()); // call the onClick in the OnItemClickListener
        }

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onClick(View view, int position);
    }

}
