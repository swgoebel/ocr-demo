package com.dababbas.myapplication.Dialogs;

import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dababbas.myapplication.R;
/**
 * Created by Christian on 23/04/2015.
 */
public class ImageDialog extends DialogFragment {

    private Bitmap bmp;

    private String title;

    public ImageDialog(){}

    public static ImageDialog New(){
        return new ImageDialog();
    }

    public ImageDialog addBitmap(Bitmap bmp) {
        if (bmp != null)
            this.bmp = bmp;
        return this;
    }

    public ImageDialog addTitle(String title) {
        if (title != null)
            this.title = title;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_dialog, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_dialog_imageView);
        final TextView textView = (TextView) view.findViewById(R.id.image_dialog_textView);

        if (bmp != null)
            imageView.setImageBitmap(bmp);

        if(title!=null)
            textView.setText(this.title);


        Button copy = (Button) view.findViewById(R.id.button2);
        Button b = (Button) view.findViewById(R.id.button);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);*/
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("yes", textView.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, textView.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        bmp.recycle();
        bmp = null;
        System.gc();
        super.onDismiss(dialog);
    }


}