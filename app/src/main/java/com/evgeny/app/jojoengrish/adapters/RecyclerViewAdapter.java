package com.evgeny.app.jojoengrish.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.activities.ListActivity;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.models.GroupModel;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Object> dataset;
    private Context context;
    private Player player = Player.getInstance();
    private final int MENU_ITEM_VIEW_TYPE = 0;
    private final int MENU_GROUP_VIEW_TYPE = 2;
    private final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, tags, groupName;
        ImageView soundPicture, groupPicture;
        LinearLayout card, groupCard;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.nameSoundPreviewTextView);
            tags = v.findViewById(R.id.tagsSoundPreviewTextView);
            soundPicture = v.findViewById(R.id.pictureSoundPreviewImageView);
            card = v.findViewById(R.id.listCard);
            ////for groups
            groupName = v.findViewById(R.id.groupName);
            groupCard = v.findViewById(R.id.groupCard);
            groupPicture=v.findViewById(R.id.imageViewGroupPicture);
        }
    }

    public RecyclerViewAdapter(Context context, List<Object> sounds) {
        this.context = context;
        this.dataset = sounds;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        LayoutInflater inflater;
        View view;
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                View nativeExpressLayoutView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.recycler_ad_unit,
                        parent, false);
                return new UnifiedNativeAdViewHolder(nativeExpressLayoutView);
            case MENU_ITEM_VIEW_TYPE:
                inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.recycler_sound_preview, parent, false);
                return new MyViewHolder(view);
            case MENU_GROUP_VIEW_TYPE:
            default:
                inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.recycler_group, parent, false);
                return new MyViewHolder(view);
        }
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                try {
                    UnifiedNativeAd unifiedNativeAd = (UnifiedNativeAd) dataset.get(position);
                    populateNativeAdView(unifiedNativeAd, ((UnifiedNativeAdViewHolder) holder).getAdView());
                } catch (Exception e) {
                    Toast.makeText(context, "Failed to load the adv", Toast.LENGTH_SHORT).show();
                }
                break;
            case MENU_ITEM_VIEW_TYPE:
                try {
                    // - get element from your dataset at this position
                    // - replace the contents of the view with that element
                    final SoundModel model = (SoundModel) dataset.get(position);
                    holder.name.setText(model.getName());
                    holder.tags.setText(model.getDescription());
                    holder.soundPicture.setImageResource(model.getPicture_adress());
                    holder.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (player.isPlaying()) {
                                player.stop();
                            } else {
                                try {
                                    player.play(context, model.getSound_adress());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Player.restartPlayer();
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.d("Sound Error", e.getMessage());
                }
            case MENU_GROUP_VIEW_TYPE:
                try {
                    // - get element from your dataset at this position
                    // - replace the contents of the view with that element
                    final GroupModel model = (GroupModel) dataset.get(position);
                    holder.groupName.setText(model.getName());
                    holder.groupPicture.setImageResource(model.getPicture_adress());
                    holder.groupCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, ListActivity.class);
                            i.putExtra("group_name", model.getName());
                            context.startActivity(i);
                        }
                    });
                } catch (Exception e) {
                    Log.d("Group Error", e.getMessage());
                }
        }


    }


    private void populateNativeAdView(UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView adView) {

        ((TextView) adView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(unifiedNativeAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        NativeAd.Image icon = unifiedNativeAd.getIcon();
        if (icon == null) {
            adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(unifiedNativeAd.getPrice());
        }
        if (unifiedNativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStarRatingView().setVisibility(View.VISIBLE);
            ((RatingBar) adView.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
        }
        if (unifiedNativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(unifiedNativeAd.getStore());
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
            ((TextView) adView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
        }
        MediaView mediaView = (MediaView) adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setNativeAd(unifiedNativeAd);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataset.get(position) instanceof SoundModel) {
            return MENU_ITEM_VIEW_TYPE;
        } else if (dataset.get(position) instanceof GroupModel) {
            return MENU_GROUP_VIEW_TYPE;
        } else {
            return UNIFIED_NATIVE_AD_VIEW_TYPE;
        }
    }

    public class UnifiedNativeAdViewHolder extends MyViewHolder {
        private UnifiedNativeAdView adView;

        public UnifiedNativeAdView getAdView() {
            return adView;
        }

        public UnifiedNativeAdViewHolder(@NonNull View view) {
            super(view);
            this.adView = view.findViewById(R.id.ad_unit);
            adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
            adView.setBodyView(adView.findViewById(R.id.ad_body));
            adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
            adView.setIconView(adView.findViewById(R.id.ad_icon));
            adView.setPriceView(adView.findViewById(R.id.ad_price));
            adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
            adView.setStoreView(adView.findViewById(R.id.ad_store));
            adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        }
    }


}


