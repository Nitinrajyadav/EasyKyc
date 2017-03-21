package com.nitin.ekyc.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by Nitin on 10/16/2016.
 */

public class CartFragRecyclerAdapter {
//        extends RecyclerView.Adapter<CartFragRecyclerAdapter.CartItemViewHolder> {

    LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<String> cartItemsArrayList;
    // private TextDrawable.IBuilder mDrawableBuilder;
    //  private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;


    public CartFragRecyclerAdapter(Context context) {
        this.context = context;
        cartItemsArrayList = new ArrayList<>();
//        mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(4).endConfig().roundRect(4);
    }

    public void addCartItems(ArrayList<String> cartItemsArrayList) {
        this.cartItemsArrayList = cartItemsArrayList;
//        notifyDataSetChanged();
    }


//    @Override
//    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (layoutInflater == null)
//            layoutInflater = LayoutInflater.from(parent.getContext());
//        View productCard = layoutInflater.inflate(R.layout.item_cart_product_holder_card, parent, false);
//
//        return new CartItemViewHolder(productCard);
//    }

//
//    @Override
//    public void onBindViewHolder(CartItemViewHolder holder, int position) {
//
//        CartItem cartItem = getCartItem(position);
//        holder.bindCartItem(cartItem);
//        holder.labelProductName.setText(cartItem.getProductName());
//        holder.labelProductBrand.setText((cartItem.getBrand()));
//        holder.labelProductWeight.setText(cartItem.getProductQuantity());
//        holder.labelProductCostPrice.setText("\u20B9 " + cartItem.getCostPrice());
//        holder.labelProductNetPrice.setText("\u20B9 " + cartItem.getNetPrice());
//
//        if (cartItem.getNetPrice() < cartItem.getCostPrice()) {
//            holder.labelProductCostPrice.setText("\u20B9 " + cartItem.getCostPrice());
//            holder.labelProductCostPrice.setPaintFlags(holder.labelProductCostPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.labelProductNetPrice.setText("\u20B9 " + cartItem.getNetPrice());
//            holder.labelProductNetPrice.setVisibility(View.VISIBLE);
//        } else {
//            holder.labelProductCostPrice.setText("\u20B9 " + cartItem.getCostPrice());
//            holder.labelProductCostPrice.setPaintFlags(holder.labelProductCostPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
//            holder.labelProductNetPrice.setVisibility(View.GONE);
//        }
//
//
//        holder.labelProductCartQty.setText("" + cartItem.getCount());
//
//
//        if (cartItem.getStock() <= 0) {
//            holder.actionIncreaseCount.setVisibility(View.GONE);
//        } else {
//            holder.actionIncreaseCount.setVisibility(View.VISIBLE);
//        }
//
////        TextDrawable itemCategoryDrawable = mDrawableBuilder.build(
////                String.valueOf(cartItem.getProductName().toUpperCase().charAt(0)),
////                mColorGenerator.getColor(cartItem.getProductName())
////        );
//
//        Glide.with(context)
//                .load(EndPoints.ASSETS_BASE_URL + cartItem.getProductImageUrl())
//                .centerCrop()
//                .placeholder(R.drawable.ic_placeholder_image)
//                .error(R.drawable.ic_placeholder_image)
//                .into(holder.productIcon);
//
//        new CartFragRecyclerAdapter.CardClickHandler(holder, position);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return cartItemsArrayList.size();
//    }
//
//    public ArrayList<CartItem> getCartItemsArrayList() {
//        return this.cartItemsArrayList;
//    }
//
//    public CartItem getCartItem(int position) {
//        return cartItemsArrayList.get(position);
//    }
//
//    public void clear() {
//        cartItemsArrayList.clear();
//    }
//
//    public void removeItem(int position) {
//        cartItemsArrayList.remove(position);
//        notifyDataSetChanged();
//    }
//
//    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
//
//        private CartItem cartItem;
//        private ImageView productIcon;
//        private Button actionDeleteItem;
//        private TextView labelProductName;
//        private TextView labelProductBrand;
//        private TextView labelProductWeight;
//        private TextView labelProductCostPrice;
//        private TextView labelProductNetPrice;
//        private TextView labelProductCartQty;
//        private ImageButton actionIncreaseCount;
//        private ImageButton actionDecreaseCount;
//
//        public CartItemViewHolder(View view) {
//            super(view);
//
//            productIcon = (ImageView) view.findViewById(R.id.imv_product_icon);
//            actionDeleteItem = (Button) view.findViewById(R.id.action_delete_item);
//            labelProductName = (TextView) view.findViewById(R.id.label_product_name);
//            labelProductBrand = (TextView) view.findViewById(R.id.label_product_brand);
//            labelProductWeight = (TextView) view.findViewById(R.id.label_product_weight);
//            labelProductCostPrice = (TextView) view.findViewById(R.id.label_product_cost_price);
//            labelProductNetPrice = (TextView) view.findViewById(R.id.label_product_net_price);
//            actionDecreaseCount = (ImageButton) view.findViewById(R.id.action_remove_from_cart);
//            labelProductCartQty = (TextView) view.findViewById(R.id.label_product_cart_qty);
//            actionIncreaseCount = (ImageButton) view.findViewById(R.id.action_add_to_cart);
//        }
//
//        public void bindCartItem(CartItem cartItem) {
//            this.cartItem = cartItem;
//        }
//
//    }
//
//    private class CardClickHandler {
//        private ImageButton actionIncreaseCount;
//        private ImageButton actionDecreaseCount;
//        private Button actionDeleteItem;
//        private CartItem cartItem;
//        private int position;
//        View.OnClickListener onDeleteItemListener = new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                mListener.onDeleteItemRequest(cartItem, position);
//            }
//        };
//        View.OnClickListener increaseCountListener = new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                Log.d("onSingleClick: --", "increaseCountListener");
//                mListener.onIncreaseProductCount(cartItem, position);
//            }
//        };
//        View.OnClickListener decreaseCountListener = new OnSingleClickListener() {
//
//            @Override
//            public void onSingleClick(View view) {
//                Log.d("onSingleClick: --", "decreaseCountListener");
//                mListener.onDecreaseProductCount(cartItem, position);
//            }
//        };
//
//        CardClickHandler(CartItemViewHolder cartItemViewHolder, int position) {
//            cartItem = cartItemViewHolder.cartItem;
//            this.position = position;
//
//            actionDeleteItem = cartItemViewHolder.actionDeleteItem;
//            actionDeleteItem.setOnClickListener(onDeleteItemListener);
//            actionIncreaseCount = cartItemViewHolder.actionIncreaseCount;
//            actionIncreaseCount.setOnClickListener(increaseCountListener);
//            actionDecreaseCount = cartItemViewHolder.actionDecreaseCount;
//            actionDecreaseCount.setOnClickListener(decreaseCountListener);
//
//            cartItemViewHolder.productIcon.setOnClickListener(new OnSingleClickListener() {
//                @Override
//                public void onSingleClick(View view) {
//                    mListener.onProductSelected(cartItem);
//                }
//            });
//
//        }
//    }
}
