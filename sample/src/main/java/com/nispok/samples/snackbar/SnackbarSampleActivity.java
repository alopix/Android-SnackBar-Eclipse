package com.nispok.samples.snackbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.nispok.snackbar.listeners.EventListener;

public class SnackbarSampleActivity extends ActionBarActivity {

    private static final String TAG = SnackbarSampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Button singleLineButton = (Button) findViewById(R.id.single_line);
        singleLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("Single-line snackbar"));
            }
        });

        Button singleLineWithActionButton = (Button) findViewById(R.id.single_line_with_action);
        singleLineWithActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("Something has been done")
                                .actionLabel("Undo")
                                .actionListener(new ActionClickListener() {
                                    @Override
                                    public void onActionClicked(Snackbar snackbar) {
                                        Toast.makeText(SnackbarSampleActivity.this,
                                                "Action undone",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }));
            }
        });

        Button multiLineButton = (Button) findViewById(R.id.multi_line);
        multiLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .type(SnackbarType.MULTI_LINE)
                                .text("This is a multi-line snackbar. Keep in mind that snackbars" +
                                        " are meant for VERY short messages"));
            }
        });

        Button multiLineWithActionButton = (Button) findViewById(R.id.multi_line_with_action);
        multiLineWithActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .type(SnackbarType.MULTI_LINE)
                                .text("This is a multi-line snackbar with an action button. Note " +
                                        "that multi-line snackbars are 2 lines max")
                                .actionLabel("Action")
                                .actionListener(new ActionClickListener() {
                                    @Override
                                    public void onActionClicked(Snackbar snackbar) {
                                        Toast.makeText(SnackbarSampleActivity.this,
                                                "Action clicked",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }));
            }
        });

        Button noAnimationButton = (Button) findViewById(R.id.no_animation);
        noAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("No animation :(")
                                .animation(false)
                                .duration(2500l));
            }
        });

        Button eventListenerButton = (Button) findViewById(R.id.event_listener);
        eventListenerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("I'm showing a toast on exit")
                                .eventListener(new EventListener() {
                                    @Override
                                    public void onShow(Snackbar snackbar) {
                                        Log.i(TAG, String.format(
                                                "Snackbar will show. Width: %d Height: %d Offset: %d",
                                                snackbar.getWidth(), snackbar.getHeight(),
                                                snackbar.getOffset()));
                                    }

                                    @Override
                                    public void onShown(Snackbar snackbar) {
                                        Log.i(TAG, String.format(
                                                "Snackbar shown. Width: %d Height: %d Offset: %d",
                                                snackbar.getWidth(), snackbar.getHeight(),
                                                snackbar.getOffset()));
                                    }

                                    @Override
                                    public void onDismiss(Snackbar snackbar) {
                                        Log.i(TAG, String.format(
                                                "Snackbar will dismiss. Width: %d Height: %d Offset: %d",
                                                snackbar.getWidth(), snackbar.getHeight(),
                                                snackbar.getOffset()));
                                    }

                                    @Override
                                    public void onDismissed(Snackbar snackbar) {
                                        Toast.makeText(SnackbarSampleActivity.this, String.format(
                                                        "Snackbar dismissed. Width: %d Height: %d Offset: %d",
                                                        snackbar.getWidth(), snackbar.getHeight(),
                                                        snackbar.getOffset()),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }));
            }
        });

        Button customColorsButton = (Button) findViewById(R.id.custom_colors);
        customColorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("Different colors!!!")
                                .textColor(Color.parseColor("#ff9d9d9c"))
                                .color(Color.parseColor("#ff914300"))
                                .actionLabel("Action")
                                .actionColor(Color.parseColor("#ff5a2900"))
                                .actionListener(new ActionClickListener() {
                                    @Override
                                    public void onActionClicked(Snackbar snackbar) {
                                        Log.i(TAG, "Action touched");
                                    }
                                })
                                .duration(Snackbar.SnackbarDuration.LENGTH_SHORT));
            }
        });

        Button unswipeableButton = (Button) findViewById(R.id.unswipeable);
        unswipeableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .text("Try to swipe me off from the screen")
                                .swipeToDismiss(false));
            }
        });

        Button indefiniteButton = (Button) findViewById(R.id.indefinite);
        indefiniteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                                .type(SnackbarType.MULTI_LINE)
                                .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                                .text("Indefinite duration, ideal for communicating errors"));
            }
        });

        Button listSampleButton = (Button) findViewById(R.id.list_example);
        listSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sampleIntent = new Intent(SnackbarSampleActivity.this,
                        SnackbarListViewSampleActivity.class);
                startActivity(sampleIntent);
            }
        });

        Button recyclerSampleButton = (Button) findViewById(R.id.recycler_example);
        recyclerSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sampleIntent = new Intent(SnackbarSampleActivity.this,
                        SnackbarRecyclerViewSampleActivity.class);
                startActivity(sampleIntent);
            }
        });

        Button customTypefaceButton = (Button) findViewById(R.id.typeface_example);
        customTypefaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-LightItalic.ttf");
                SnackbarManager.show(
                        Snackbar.with(SnackbarSampleActivity.this)
                        .text("Custom font!")
                        .textTypeface(tf)
                        .actionLabel("Cool")
                        .actionLabelTypeface(tf));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.visit_repo:
                goToRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToRepo() {
        Uri uri = Uri.parse(getString(R.string.repo_url));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
