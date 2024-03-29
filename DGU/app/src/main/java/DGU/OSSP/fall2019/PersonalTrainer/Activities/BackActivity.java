package DGU.OSSP.fall2019.PersonalTrainer.Activities;


import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import DGU.OSSP.fall2019.DGU.R;

public class BackActivity extends YouTubeBaseActivity {

    private final String API_KEY2 = "AIzaSyCvXeZ8vj_n9Qki2iqw_b3W2RW_ClDMujk";
    private final String VIDEO_CODE2 = "vNzxQN-1kDI";

    YouTubePlayerView player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);


        player2 = (YouTubePlayerView)findViewById(R.id.player2);
        player2.initialize(API_KEY2, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(!b){
                    youTubePlayer.loadVideo(VIDEO_CODE2);
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
