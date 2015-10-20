package app.com.example.kiran.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private final String LOG_TAG                = DetailActivity.class.getSimpleName();
    private final String FORECAST_SHARE_HASTAG  =   "#SunshineApp";
    private String mForeCastStr                 =   null;
    ShareActionProvider mShareActionProvider    =   null;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        if(intent !=null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
             mForeCastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView)rootView.findViewById(R.id.detailText)).setText(mForeCastStr);

        }

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //<!-- Inflate the menu , this add item to action bar if it is present-->
        inflater.inflate(R.menu.detailfragment, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Get the provider and hold onto it to set/change the share intent
        //Attach an intent to this ShareActionProvider, you can update this at any time
        //Like when the user select a new piece of data they might like to share
        if(mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(createShareForeCastIntent());

        }else
        {
            Log.d("SocialShare App", "Share Action Provider is null?");
        }
    }

   private Intent createShareForeCastIntent()
   {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        //shareIntent.setType("text/plane");
        shareIntent.setType("image/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mForeCastStr +FORECAST_SHARE_HASTAG);
        return shareIntent;
    }

}
