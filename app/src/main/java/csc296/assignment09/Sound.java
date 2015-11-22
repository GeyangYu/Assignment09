package csc296.assignment09;

/**
 * Created by yugeyang on 15-11-18.
 */
public class Sound {
    private final String mPath;
    private final String mName;
    private Integer mId;

    public Sound(String path, String name) {
        mPath = path;
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
