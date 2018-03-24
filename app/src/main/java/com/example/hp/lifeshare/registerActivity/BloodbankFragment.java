package com.example.hp.lifeshare.registerActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;

import java.io.File;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BloodbankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BloodbankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodbankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText etname,etaddress,etphone;
    Button bfront,bsubmit;
    String picturePath;
    final static int MY_REQUEST_CODE = 100;
    private OnFragmentInteractionListener mListener;

    public BloodbankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BloodbankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BloodbankFragment newInstance(String param1, String param2) {
        BloodbankFragment fragment = new BloodbankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myView= inflater.inflate(R.layout.fragment_user, container, false);
        etname=(EditText)myView.findViewById(R.id.etname);
        etphone=(EditText)myView.findViewById(R.id.etphn);
        etaddress=(EditText)myView.findViewById(R.id.etadd);
        bfront=(Button) myView.findViewById(R.id.documentFront);
        bsubmit=(Button)myView.findViewById(R.id.bsubmit);


        bfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/LifeShare/documents");
                if(!myDir.exists())
                    myDir.mkdirs();
                String ctime=System.currentTimeMillis()+"";
                String fname = "front"+ctime+".jpg";
                File file = new File (myDir, fname);
                Log.e("camera path",""+file);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, 0012);
                picturePath=file.getPath();
                bfront.setText(""+fname);
                PreferenceHelper.setDonorFront(myView.getContext(),picturePath);

            }
        });



        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =etname.getText().toString();
                String phone=etphone.getText().toString();
                String address=etaddress.getText().toString();

               // PreferenceHelper.setBloodBankDetails(myView.getContext(),name,phone,address);


               /* Log.e("email",""+PreferenceHelper.getdetailsEmail(myView.getContext()));
                Log.e("name",""+PreferenceHelper.getdetailsName(myView.getContext()));
                Log.e("front",""+PreferenceHelper.getdetailsFront(myView.getContext()));
                Log.e("back",""+PreferenceHelper.getdetailsBack(myView.getContext()));
                Log.e("dob",""+PreferenceHelper.getdetailsDob(myView.getContext()));
                Log.e("group",""+PreferenceHelper.getdetailsBgroup(myView.getContext()));*/

                startActivity(new Intent(myView.getContext(),MapsActivity.class));


            }
        });



        return myView;  }

    // TODO: Rename method, update argument and hook method into UI event


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
