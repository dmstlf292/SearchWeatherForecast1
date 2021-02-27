package com.info.searchweather.fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.info.searchweather.R;
import com.info.searchweather.listener.SearchFragmentToActivityListener;

public class SearchFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {

    private EditText mEditText;
    private Button mSearchButton;
    private SearchFragmentToActivityListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mEditText = (EditText) view.findViewById(R.id.edittext);
        mEditText.setOnKeyListener(this);
        mSearchButton = (Button) view.findViewById(R.id.search_btn);
        mSearchButton.setOnClickListener(this);

        return view;
    }

    public void setListener(SearchFragmentToActivityListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            onClick(mSearchButton);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String keyword = mEditText.getText().toString();

        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(getActivity(), "검색어를 입력해주세요!", Toast.LENGTH_SHORT).show();
        } else {

            if (mListener != null) {
                mListener.receivedDataFromSearchFragment(keyword);
            }

        }

    }
}