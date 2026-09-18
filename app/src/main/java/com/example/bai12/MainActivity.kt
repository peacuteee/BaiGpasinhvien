package com.example.bai12

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bai12.databinding.ActivityMainBinding
import com.example.bai12.model.Student
import com.example.bai12.utils.showConfirmDialog
import com.example.bai12.utils.toAcademicRanking
import com.example.bai12.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentStudent = Student(
        id = "23115053122139",
        name = "Trần Thanh Tài",
        className = "CNTT23",
        email = "23115053122139@sv.ute.udn.vn",
        phone = "012345678",
        gpa = 3.3
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentStudent.phone}")
            }
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            showConfirmDialog(
                title = "Xác nhận xóa",
                message = "Bạn có chắc chắn muốn xóa hồ sơ của sinh viên ${currentStudent.name} không?"
            ) {
                // Lambda function chạy khi ấn "Đồng ý"
                binding.cardProfile.visibility = View.GONE
                toast("Đã xóa hồ sơ sinh viên!")
            }
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvPhone.text = "SĐT: ${student.phone} • Email: ${student.email}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}