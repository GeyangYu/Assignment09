package csc296.assignment09;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class SoundFragment extends Fragment {

    private static final String TAG = "SoundFragmentLog";
    private Radio mRadio;

    public interface DetailItemClickListener {
        public void OnDetailItemClick(CharSequence text);
    }

    private DetailItemClickListener mListener;

    public SoundFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DetailItemClickListener)context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (DetailItemClickListener)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() called");
        setRetainInstance(true);
        mRadio = new Radio(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() called");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sound, container, false);

        RecyclerView recycler = (RecyclerView)view.findViewById(R.id.recycler_view_sound);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new SoundAdapter(mRadio.getSounds()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
        mRadio.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder  {
        private Button mSoundName;
        private Sound mSound;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.view_sound, container, false));

            mSoundName = (Button)itemView.findViewById(R.id.button);
        }

        public void bind(Sound sound) {
            mSound = sound;
            mSoundName.setText(sound.getName());

            mSoundName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRadio.play(mSound);
                    mListener.OnDetailItemClick(mSound.getName());
                }
            });
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            holder.bind(mSounds.get(position));
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
