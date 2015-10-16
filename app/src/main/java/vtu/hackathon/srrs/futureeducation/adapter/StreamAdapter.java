package vtu.hackathon.srrs.futureeducation.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.dragsortadapter.DragSortAdapter;

import java.util.List;

import vtu.hackathon.srrs.futureeducation.AppController;
import vtu.hackathon.srrs.futureeducation.R;
import vtu.hackathon.srrs.futureeducation.Supporting_Files.FadeInNetworkImageView;
import vtu.hackathon.srrs.futureeducation.dto.StreamDTO;

/**
 * Created by sathish on 17/10/15.
 */
public class StreamAdapter extends UltimateViewAdapter {

    Activity activity;
    StreamDTO eachRow;
    private List<StreamDTO> listOfRow;
    onSendPageListener sendPageListener;


    // initialize the adapter
    public StreamAdapter(Activity activity, List<StreamDTO> listOfRow, onSendPageListener sendPageListener){//, onCallListener callListener) {
        this.activity = activity;
        this.listOfRow = listOfRow;
        this.sendPageListener = sendPageListener;
    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_stream, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public int getAdapterItemCount() {
        return listOfRow.size();
    }

    @Override
    public long generateHeaderId(int position) {
        if (position == 0) {
            return position;
        } else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if ((customHeaderView != null ? position <= getAdapterItemCount() : position < getAdapterItemCount())
                && (customHeaderView == null || position > 0)) {
            try {

                ImageLoader imageLoader = AppController.getInstance().getImageLoader();

                eachRow = listOfRow.get(position - 1);

                ((ViewHolder) holder).image.setImageUrl(eachRow.getImage(), imageLoader);


                ((ViewHolder) holder).content.setText(eachRow.getContent());


                ((ViewHolder) holder).heading.setText(eachRow.getHeading());
//
//
//                ((ViewHolder) holder).alumniDept.setText(eachRow.getDepartment());
//
//                ((ViewHolder) holder).alumniYear.setText(eachRow.getYear());
//
//                ((ViewHolder) holder).alumniPhone.setText(eachRow.getPhone());



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_stream, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public <T> void insert(List<T> list, T object, int position) {
        list.add(object);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }



    class ViewHolder extends UltimateRecyclerviewViewHolder {
        FadeInNetworkImageView image;
        CardView card;
        TextView heading, content;

        //intialize the custom layout

        public ViewHolder(View itemView) {
            super(itemView);
            image = (FadeInNetworkImageView)
                    itemView.findViewById(R.id.imageStream);

            heading = (TextView) itemView.findViewById(R.id.headingStream);

            content = (TextView) itemView.findViewById(R.id.contentStream);

           card = (CardView) itemView.findViewById(R.id.card_view);



            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sendPageListener.sendPageLink(
                            listOfRow.get(getLayoutPosition() - 1).getPage());
                }
            });


        }


    }


    public interface onSendPageListener {
        public void sendPageLink(String pageLink);
    }

}
