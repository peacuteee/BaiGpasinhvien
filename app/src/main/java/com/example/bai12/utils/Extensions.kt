package com.example.bai12.utils

import android.content.Context
import android.widget.Toast

// Hàm tiện ích để hiển thị Toast nhanh gọn
fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// Hàm tiện ích để xếp loại học lực dựa trên GPA
fun Double.toAcademicRanking(): String {
    return when {
        this >= 3.6 -> "Xuất sắc"
        this >= 3.2 -> "Giỏi"
        this >= 2.5 -> "Khá"
        this >= 2.0 -> "Trung bình"
        else -> "Yếu"
    }
}