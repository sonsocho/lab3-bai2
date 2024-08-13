package com.example.lab3_bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvKQ;
    private double giaTriDauTien = Double.NaN;
    private double giaTriThuHai;
    private char phepToanHienTai;

    private final char PHEP_CONG = '+';
    private final char PHEP_TRU = '-';
    private final char PHEP_NHAN = '*';
    private final char PHEP_CHIA = '/';
    private final char PHEP_BANG = '=';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvKQ = findViewById(R.id.tvResult);

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnCong = findViewById(R.id.btnAdd);
        Button btnTru = findViewById(R.id.btnSubtract);
        Button btnNhan = findViewById(R.id.btnMultiply);
        Button btnChia = findViewById(R.id.btnDivide);
        Button btnBang = findViewById(R.id.btnEquals);
        Button btnXoa = findViewById(R.id.btnClear);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                tvKQ.append(button.getText());
            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);

        btnCong.setOnClickListener(v -> {
            tinhToan();
            phepToanHienTai = PHEP_CONG;
            tvKQ.setText(String.valueOf(giaTriDauTien) + "+");
        });

        btnTru.setOnClickListener(v -> {
            tinhToan();
            phepToanHienTai = PHEP_TRU;
            tvKQ.setText(String.valueOf(giaTriDauTien) + "-");
        });

        btnNhan.setOnClickListener(v -> {
            tinhToan();
            phepToanHienTai = PHEP_NHAN;
            tvKQ.setText(String.valueOf(giaTriDauTien) + "*");
        });

        btnChia.setOnClickListener(v -> {
            tinhToan();
            phepToanHienTai = PHEP_CHIA;
            tvKQ.setText(String.valueOf(giaTriDauTien) + "/");
        });

        btnBang.setOnClickListener(v -> {
            tinhToan();
            phepToanHienTai = PHEP_BANG;
            tvKQ.setText(tvKQ.getText().toString() + String.valueOf(giaTriThuHai) + "=" + String.valueOf(giaTriDauTien));
            giaTriDauTien = Double.NaN;
            phepToanHienTai = '0';
        });

        btnXoa.setOnClickListener(v -> {
            giaTriDauTien = Double.NaN;
            giaTriThuHai = Double.NaN;
            tvKQ.setText("");
        });
    }

    private void tinhToan() {
        String vanBanHienTai = tvKQ.getText().toString();

        // Kiểm tra nếu chuỗi hiện tại không rỗng
        if (vanBanHienTai.isEmpty()) {
            return;
        }

        // Nếu giaTriDauTien đã được xác định
        if (!Double.isNaN(giaTriDauTien)) {
            // Tách các giá trị từ chuỗi hiện tại dựa trên phép toán
            try {
                // Tìm toán tử hiện tại
                String[] giaTri = vanBanHienTai.split("[+\\-*/]");
                if (giaTri.length == 2) {
                    giaTriThuHai = Double.parseDouble(giaTri[1]);
                } else if (giaTri.length == 1) {
                    // Nếu chỉ có một giá trị (trong trường hợp sử dụng dấu "=" đầu tiên)
                    giaTriThuHai = Double.parseDouble(giaTri[0]);
                }
            } catch (NumberFormatException e) {
                // Nếu xảy ra lỗi khi chuyển đổi chuỗi thành số
                tvKQ.setText("Lỗi");
                giaTriDauTien = Double.NaN;
                return;
            }

            // Thực hiện phép toán dựa trên toán tử hiện tại
            switch (phepToanHienTai) {
                case PHEP_CONG:
                    giaTriDauTien += giaTriThuHai;
                    break;
                case PHEP_TRU:
                    giaTriDauTien -= giaTriThuHai;
                    break;
                case PHEP_NHAN:
                    giaTriDauTien *= giaTriThuHai;
                    break;
                case PHEP_CHIA:
                    if (giaTriThuHai != 0) {
                        giaTriDauTien /= giaTriThuHai;
                    } else {
                        tvKQ.setText("Lỗi");
                        giaTriDauTien = Double.NaN;
                        return;
                    }
                    break;
            }
        } else {
            // Nếu giaTriDauTien chưa được xác định, đặt giá trị của nó
            try {
                giaTriDauTien = Double.parseDouble(vanBanHienTai);
            } catch (NumberFormatException e) {
                tvKQ.setText("Lỗi");
                giaTriDauTien = Double.NaN;
            }
        }
    }

}
