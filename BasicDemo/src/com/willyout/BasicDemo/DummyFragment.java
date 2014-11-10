package com.willyout.BasicDemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by will on 2014/9/19.
 */
public class DummyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.START);
        Bundle args = getArguments();
        textView.setText(args.getInt("selection_number") + "");
        textView.setTextSize(30);

        return textView;
    }
}
