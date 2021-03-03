package com.example.pyatv1;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.widget.Toast;
import java.io.IOException;

public class SoundGames {
    public  Context context;
    public SoundPool mSoundPool;
    public int mCatSound;
    public int mStreamID;
    public AssetManager mAssetManager;


    public SoundGames(Context context){
        this.context = context;
    }


    public void initSound(String fileName) {

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        } else {
            // Для новых устройств
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }
        mAssetManager = context.getAssets();
        // получим идентификаторы
        mCatSound = loadSound(fileName);
    }



    public int playSound(boolean MusicOn) {
        if (mCatSound > 0 && MusicOn == true) {
            mStreamID = mSoundPool.play(mCatSound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    public int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    }