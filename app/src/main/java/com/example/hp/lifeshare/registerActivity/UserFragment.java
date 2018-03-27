package com.example.hp.lifeshare.registerActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.lifeshare.MainActivity;
import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText etname,etdob,etphone;
    Button bfront,bback,bsubmit;
    DatePickerDialog mDatePicker;
    Spinner sgroup;
    String bloodgroup;
    String picturePath;
    final static int MY_REQUEST_CODE = 100;
    private OnFragmentInteractionListener mListener;
    private RadioGroup radioGroup;
    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myView= inflater.inflate(R.layout.fragment_user, container, false);
        etname=(EditText)myView.findViewById(R.id.etname);

        etphone=(EditText)myView.findViewById(R.id.etphn);
        etdob=(EditText)myView.findViewById(R.id.etdob);
        bfront=(Button) myView.findViewById(R.id.documentFront);
        bback=(Button)myView.findViewById(R.id.documentBack);
        bsubmit=(Button)myView.findViewById(R.id.bsubmit);
        sgroup=(Spinner)myView.findViewById(R.id.bloodGroup);
//        radioGroup = (RadioGroup) myView.findViewById(R.id.radioGroup);
  //      radioGroup.clearCheck();
/*
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(getActivity(), rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });*/


        /*
        List<String> repeatType = db.getRepeatType();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repeatType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeat.setAdapter(adapter);
*/
        String[] bgroups=new String[]{"O+","O-","A+","A-","B+","B-","AB+","AB-"};
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(myView.getContext(),android.R.layout.simple_spinner_item,bgroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sgroup.setAdapter(adapter);


        etdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                mDatePicker = new DatePickerDialog(myView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        etdob.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        etdob.clearFocus();
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });

        bfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 001);
                }
                else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/LifeShare/documents");
                    if (!myDir.exists())
                        myDir.mkdirs();
                    String ctime = System.currentTimeMillis() + "";
                    String fname = "front" + ctime + ".jpg";
                    File file = new File(myDir, fname);
                    Log.e("camera path", "" + file);
                    //File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, 0012);
                    picturePath = file.getPath();
                    bfront.setText("" + fname);
                    PreferenceHelper.setDonorFront(myView.getContext(), picturePath);
                }
            }
        });

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 001);
                }
                else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/LifeShare/documents");
                    if (!myDir.exists())
                        myDir.mkdirs();
                    String ctime = System.currentTimeMillis() + "";
                    String fname = "back" + ctime + ".jpg";
                    File file = new File(myDir, fname);
                    Log.e("camera path", "" + file);
                    //File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, 0012);
                    picturePath = file.getPath();
                    bback.setText("" + fname);
                    PreferenceHelper.setDonorBack(myView.getContext(), picturePath);

                }

            }
        });
        sgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Get repeat type
//                String name=ge
  //              Repeat=position;
    //            Log.e("Repeat type1",""+Repeat);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
      //  Log.e("Repeat",""+Repeat);


        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =etname.getText().toString();
                String dob=etdob.getText().toString();
                String phnNo=etphone.getText().toString();
                String bgroup= sgroup.getSelectedItem().toString();
                if((name.length()==0)||(dob.length()==0)||(phnNo.length()==0))
                {
                    Toast.makeText(getActivity(), "Please Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.e("blood group", "" + bgroup);
                    PreferenceHelper.setDonorDetails(myView.getContext(), name, dob);
                    PreferenceHelper.setDonorBgroup(myView.getContext(), bgroup);
                    PreferenceHelper.setdetailsPhonr(myView.getContext(), phnNo);

                    Log.e("email", "" + PreferenceHelper.getdetailsEmail(myView.getContext()));
                    Log.e("name", "" + PreferenceHelper.getdetailsName(myView.getContext()));
                    Log.e("front", "" + PreferenceHelper.getdetailsFront(myView.getContext()));
                    Log.e("back", "" + PreferenceHelper.getdetailsBack(myView.getContext()));
                    Log.e("dob", "" + PreferenceHelper.getdetailsDob(myView.getContext()));
                    Log.e("group", "" + PreferenceHelper.getdetailsBgroup(myView.getContext()));

                    startActivity(new Intent(myView.getContext(), MapsActivity.class));
                    getActivity().getFragmentManager().popBackStack();
                }

            }
        });


//        etdob.s
        return myView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode)
            {
                case 0012:
                    try {

                        Log.e("pathhhh",""+picturePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
