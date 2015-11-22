package csc296.assignment09;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    private static final String ARG_MESSAGE = "ARG_MESSAGE";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newFragment(CharSequence message) {
        Bundle args = new Bundle();
        args.putCharSequence(ARG_MESSAGE, message);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle args = getArguments();
        CharSequence message = args.getCharSequence(ARG_MESSAGE);
        TextView textViewDetail = (TextView)view.findViewById(R.id.text_view_detail);
        textViewDetail.setText(message);

        return view;
    }

}
