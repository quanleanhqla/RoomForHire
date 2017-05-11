package com.example.quanla.roomforhire.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.dataFake.DataUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    @BindView(R.id.edt_title)
    EditText edt_title;
    @BindView(R.id.edt_price)
    EditText edt_price;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.edt_dientich)
    EditText edt_dientich;
    @BindView(R.id.sp_vung)
    Spinner sp_vung;
    @BindView(R.id.sp_type)
    Spinner sp_type;
    @BindView(R.id.sp_state)
    Spinner sp_state;
    @BindView(R.id.edt_detail)
    EditText edt_detail;
    @BindView(R.id.btn_post)
    Button btn_post;
    @BindView(R.id.sp_danhmuc)
    Spinner sp_danhmuc;
    @BindView(R.id.ib_anh)
    ImageButton ib_anh;

    private String vung;
    private String state;
    private String type;
    private String danhmuc;

    private DatabaseReference data;
    private StorageReference storage;
    private static final int GALLERY_REQUEST = 1;
    private ProgressDialog progress;
    private Uri imageUri;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        progress = new ProgressDialog(this.getContext());
        storage = FirebaseStorage.getInstance().getReference();
        data = FirebaseDatabase.getInstance().getReference().child("new");

        setupUI();
        ib_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
        return view;
    }

    private void setupUI() {
        final String arr_danhmuc[] = {"Danh mục", "room", "apartment", "villa"};
        ArrayAdapter<String> adapter_danhmuc= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, arr_danhmuc);
        adapter_danhmuc.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp_danhmuc.setAdapter(adapter_danhmuc);
        sp_danhmuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                danhmuc = arr_danhmuc[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final String arr_vung[] = {"Khu vực", "Quận Hoàn Kiếm", "Quận Ba Đình",
                "Quận Đống Đa", "Quận Hai Bà Trưng", "Quận Thanh Xuân",
                "Quận Tây Hồ", "Quận Cầu Giấy", "Quận Hoàng Mai",
                "Quận Long Biên", "Huyện Đông Anh", "Huyện Sóc Sơn",
                "Huyện Sóc Sơn", "Huyện Thanh Trì", "Quận Hà Đông",
                "Thị Xã Sơn Tây", "Huyện Đan Phượng", "Huyện Hoài Đức",
                "Huyện Quốc Oai", "Huyện Thạch Thất", "Huyện Chương Mỹ",
                "Huyện Thường Tín", "Huyện Phú Xuyên", "Quận Bắc Từ Liêm",
                "Huyện Ba Vì", "Huyện Gia Lâm", "Huyện Mê Linh",
                "Huyện Mỹ Đức", "Huyện Phúc Thọ", "Huyện Thanh Oai",
                "Huyện Ứng Hòa"};

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, arr_vung);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp_vung.setAdapter(adapter);
        sp_vung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vung = arr_vung[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String arr_state[] = {"Tình trạng", "Chưa qua sử dụng", "Đã qua sử dụng"};
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, arr_state);
        adapter_state.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp_state.setAdapter(adapter_state);
        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = arr_state[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String arr_type[] = {"Loại", "Cần bán", "Cần cho thuê"};

        ArrayAdapter<String> adapter_type = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, arr_type);
        adapter_type.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp_type.setAdapter(adapter_type);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = arr_type[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Get content
        if(requestCode==GALLERY_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            ib_anh.setImageURI(imageUri);
        }
    }

    public void post(){
        progress.setMessage("Đang đăng tin...");
        progress.show();
        final String title = edt_title.getText().toString().trim();
        final String price = edt_price.getText().toString().trim();
        final String address = edt_address.getText().toString().trim();
        final String dientich = edt_dientich.getText().toString().trim();
        final String detail = edt_detail.getText().toString().trim();
        final String vung = this.vung;
        final String type = this.type;
        final String state = this.state;
        String danhmuc = this.danhmuc;

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(address) &&!TextUtils.isEmpty(price)
                && !TextUtils.isEmpty(dientich) && !TextUtils.isEmpty(detail) && !TextUtils.isEmpty(vung)
                && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(state)){
            StorageReference filePath = storage.child(danhmuc).child(title).child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newData = data.child(title);
                    newData.child("title").setValue(title);
                    newData.child("price").setValue(price);
                    newData.child("address").setValue(address);
                    newData.child("dientich").setValue(dientich);
                    newData.child("detail").setValue(detail);
                    newData.child("vung").setValue(vung);
                    newData.child("type").setValue(type);
                    newData.child("state").setValue(state);
                    newData.child("host").setValue(DataUser.instance.getAllRoom().get(0).getName());
                    newData.child("hostAddress").setValue(DataUser.instance.getAllRoom().get(0).getAddress());
                    newData.child("phone").setValue(DataUser.instance.getAllRoom().get(0).getPhone());
                    newData.child("check").setValue(-1);
                    newData.child("url").setValue(downloadUrl.toString());
                    ib_anh.setImageResource(R.drawable.ic_picture);
                    edt_title.setText(null);
                    edt_price.setText(null);
                    edt_address.setText(null);
                    edt_dientich.setText(null);
                    edt_detail.setText(null);
                    sp_danhmuc.setSelection(0);
                    sp_state.setSelection(0);
                    sp_vung.setSelection(0);
                    sp_type.setSelection(0);
                    Toast.makeText(getContext(), "Tin của bạn đã được đăng", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });

        }
        else Toast.makeText(this.getContext(), "Bạn nhập thiếu", Toast.LENGTH_SHORT).show();
    }

}
