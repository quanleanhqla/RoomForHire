package com.example.quanla.roomforhire.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanla.roomforhire.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StreamDownloadTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadActivity extends AppCompatActivity {

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

    private String vung;
    private String state;
    private String type;

    private DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        data = FirebaseDatabase.getInstance().getReference().child("new");
        setupUI();
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

    }

    private void setupUI() {
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

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr_vung);
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
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr_state);
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

        ArrayAdapter<String> adapter_type = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr_type);
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

    public void post(){
        String title = edt_title.getText().toString().trim();
        String price = edt_price.getText().toString().trim();
        String address = edt_address.getText().toString().trim();
        String dientich = edt_dientich.getText().toString().trim();
        String detail = edt_detail.getText().toString().trim();
        String vung = this.vung;
        String type = this.type;
        String state = this.state;

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(address) &&!TextUtils.isEmpty(price)
                && !TextUtils.isEmpty(dientich) && !TextUtils.isEmpty(detail) && !TextUtils.isEmpty(vung)
                && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(state)){
            DatabaseReference newData = data.child(title);
            newData.child("title").setValue(title);
            newData.child("price").setValue(price);
            newData.child("address").setValue(address);
            newData.child("dientich").setValue(dientich);
            newData.child("detail").setValue(detail);
            newData.child("vung").setValue(vung);
            newData.child("type").setValue(type);
            newData.child("state").setValue(state);
            Toast.makeText(this, "Đăng tin thành công", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Bạn nhập thiếu", Toast.LENGTH_SHORT).show();
    }
}
