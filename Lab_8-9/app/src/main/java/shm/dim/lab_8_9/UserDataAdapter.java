package shm.dim.lab_8_9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<User> users;
    private Context context;
    private final OnItemClickListener listener;
    private static int position;


    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }

    UserDataAdapter(Context context, List<User> users, OnItemClickListener listener) {
        this.context = context;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    @Override
    public UserDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final UserDataAdapter.ViewHolder holder, int position) {
        User user = users.get(position);

        holder.nameView.setText(user.getName() + " " + user.getSurname());
        holder.groupView.setText(user.getGroup());
        Glide.with(context)
                .load(user.getPhotoUri())
                .into(holder.imageView);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0,  0, "Удалить");
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });

        holder.bind(users.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateList(List<User> list) {
        users = list;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final CardView mCardView;
        final ImageView imageView;
        final TextView nameView, groupView;


        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            nameView =  view.findViewById(R.id.name);
            groupView =  view.findViewById(R.id.group);
            mCardView = view.findViewById(R.id.cardView);
        }

        public void bind(final User item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDataAdapter.this.listener.onItemClick(item);
                }
            });
        }
    }
}