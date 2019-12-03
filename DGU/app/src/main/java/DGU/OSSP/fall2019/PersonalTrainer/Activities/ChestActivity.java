package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import DGU.OSSP.fall2019.DGU.R;

public class ChestActivity extends YouTubeBaseActivity {

    private final String API_KEY = "AIzaSyCvXeZ8vj_n9Qki2iqw_b3W2RW_ClDMujk";
    private final String VIDEO_CODE = "Cse56EbcBSk";

    YouTubePlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);


        player = (YouTubePlayerView)findViewById(R.id.player);
        player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(!b){
                    youTubePlayer.loadVideo(VIDEO_CODE);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                //Toast.makeText(ChestActivity.this, YouTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

