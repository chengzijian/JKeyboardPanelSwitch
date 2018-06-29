package cn.dreamtobe.kpswitch.demo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.dreamtobe.kpswitch.demo.R;

/**
 * Created by zijian.cheng on 2018/6/27.
 */

public class ChattingNoCompressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live_blank, container, false);
    }
}
