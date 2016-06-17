package edx.android.galileo.gustavo.twitterclient.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gustavo on 15/06/16.
 */
public interface TimeLineService {
    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeLine(@Query("count") Integer count,
                      @Query("trim_user") boolean trim_user,
                      @Query("exclude_replies") boolean exclude_replies,
                      @Query("contributor_details") boolean contributorDetails,
                      @Query("include_entities") boolean include_entities,
                      Callback<List<Tweet>>callback
                      );
}
