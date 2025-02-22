package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.OrderCompListAdapter;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.OrderDetAdapter;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;

public class OrderFragment extends Fragment {
    private static final String TAG = "OrderFragment";
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    private Activity activity;
    private ImageView iv_back_01;
//    private RecyclerView rv_OrderDetFm_01;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButton(view);
//        handleOrderDetRecyclerView();
    }


    private void findViews(View view) {
        iv_back_01 = view.findViewById(R.id.iv_back_01);
//        rv_OrderDetFm_01 = view.findViewById(R.id.rv_OrderDetFm_01);
    }

    private void handleButton(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.orderFragment, true);
        });
    }

//    private void handleOrderDetRecyclerView() {
//        //商品讀入
//        List<ShoppingCarMerch> shoppingCarFile = orderComploadMerchFile();
//        List<ShoppingCarMerch> orderCompMerchFile = new ArrayList<>();
//
//        for (int i = 0; i < shoppingCarFile.size(); i++) {
//            if (shoppingCarFile.get(i).getMerchCheckBox()) {
//                orderCompMerchFile.add(shoppingCarFile.get(i));
//            }
//        }
//
//        for (int i = shoppingCarFile.size() - 1; i >= 0; i--) {
//            if (shoppingCarFile.get(i).getMerchCheckBox()) {
//                shoppingCarFile.remove(i);
//            }
//        }
//
//
//        //商品存檔
//        orderCompSaveMerchFile(shoppingCarFile);
//
//        rv_OrderDetFm_01.setAdapter(new OrderCompListAdapter(activity, orderCompMerchFile));
//        rv_OrderDetFm_01.setLayoutManager(new LinearLayoutManager(activity));
//    }

    /**
     * 商品讀檔
     */


    private List<ShoppingCarMerch> orderComploadMerchFile() {
        try (
                // 取得FileInputStream物件
                FileInputStream fis = activity.openFileInput(SHOPPINGCARLIST);
                // Java I/O相關程式
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<ShoppingCarMerch>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * 商品存檔
     */
    private void orderCompSaveMerchFile(final List<ShoppingCarMerch> shoppingCarMerch) {
        try (
                // 取得FileOutputStream物件
                FileOutputStream fos = activity.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
                // Java I/O相關程式
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(shoppingCarMerch);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}