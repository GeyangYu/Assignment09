package csc296.assignment09;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yugeyang on 15-11-18.
 */
public class Radio {
    private static final String TAG = "RadioLog";
    private static final String SOUND_FOLDER = "sound";

    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;

    public Radio(Context context) {
        mAssets = context.getAssets();
        mSounds = new ArrayList<Sound>();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUND_FOLDER);
            int i = 1;
            for(String filename : soundNames) {
                String path = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(path, filename);
                mSounds.add(sound);

                try {
                    AssetFileDescriptor afd = mAssets.openFd(sound.getPath());
                    int soundId = mSoundPool.load(afd, 1);
                    sound.setId(soundId);
                }
                catch(IOException ioe) {
                    Log.e(TAG, "could not load sound from file: " + sound.getPath(), ioe);
                }

                i = i + 1;
            }
        }
        catch(IOException ioe) {
            Log.e(TAG, "could not load sound files.", ioe);
        }
    }

    public void play(Sound sound) {
        Integer id = sound.getId();
        if(id != null) {
            mSoundPool.play(
                    id,   // sound id
                    1.0f, // left volume
                    1.0f, // right volume
                    1,    // priority (ignored)
                    0,    // loop counter, 0 for no loop
                    1.0f  // playback rate
            );
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void release() {
        mSoundPool.release();
    }
}
