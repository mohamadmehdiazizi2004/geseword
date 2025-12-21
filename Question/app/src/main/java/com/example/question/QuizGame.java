package com.example.question;

import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        // لیست سوالات
        Question[] questions = new Question[] {
                new Question("کدام یک از این گزینه‌ها پایتون را به درستی معرفی می‌کند؟",
                        new String[] {"زبان برنامه‌نویسی", "نوعی ماشین", "یک نوع شبکه", "سیستم‌عامل"},
                        0),
                new Question("پایتون چه نوع زبانی است؟",
                        new String[] {"زبان سطح پایین", "زبان سطح بالا", "زبان ماشین", "زبان اسمبلی"},
                        1),
                new Question("کدام کتابخانه برای پردازش داده‌ها در پایتون استفاده می‌شود؟",
                        new String[] {"React", "NumPy", "Django", "Angular"},
                        1)
        };

        Scanner scanner = new Scanner(System.in);

        int score = 0; // امتیاز بازی

        // پرسیدن سوالات
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];

            System.out.println("سوال " + (i + 1) + ": " + question.getQuestionText());
            String[] options = question.getOptions();

            // نمایش گزینه‌ها
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            // دریافت پاسخ از کاربر
            System.out.print("جواب خود را وارد کنید (۱ تا ۴): ");
            int userAnswer = scanner.nextInt() - 1;  // تغییر به ایندکس صحیح

            // بررسی پاسخ
            if (userAnswer == question.getCorrectAnswerIndex()) {
                System.out.println("درست بود! \uD83D\uDC4C");
                score++;
            } else {
                System.out.println("غلط بود! ❌");
            }
            System.out.println();  // خط جدید برای جدا کردن سوالات
        }

        // نمایش نتیجه نهایی
        System.out.println("بازی تمام شد! امتیاز شما: " + score + "/" + questions.length);
        scanner.close();
    }
}
