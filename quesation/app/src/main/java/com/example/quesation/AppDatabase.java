package com.example.quesation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {QuizQuestion.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract QuestionDao questionDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "quiz_db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        // دیتابیس ساخته شد → سوال‌ها را پر کن
                                        AppDatabase database = getInstance(context);
                                        QuestionDao dao = database.questionDao();
                                        if (dao.count() == 0) {
                                            dao.insertAll(seedQuestions());
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static List<QuizQuestion> seedQuestions() {
        List<QuizQuestion> q = new ArrayList<>();

        // 20 سوال جغرافیا (نمونه دقیق)
        q.add(new QuizQuestion("پایتخت ایران کدام است؟", "تهران", "تبریز", "اصفهان", "شیراز", 0));
        q.add(new QuizQuestion("بزرگ‌ترین اقیانوس جهان کدام است؟", "اطلس", "هند", "آرام", "منجمد شمالی", 2));
        q.add(new QuizQuestion("رود نیل در کدام قاره است؟", "آسیا", "آفریقا", "اروپا", "آمریکا", 1));
        q.add(new QuizQuestion("کوه اورست در کدام رشته‌کوه قرار دارد؟", "آلپ", "هیمالیا", "زاگرس", "آند", 1));
        q.add(new QuizQuestion("پایتخت ترکیه کدام است؟", "استانبول", "آنکارا", "ازمیر", "آنتالیا", 1));
        q.add(new QuizQuestion("کشور ژاپن در کدام قاره است؟", "اروپا", "آفریقا", "آسیا", "آمریکا", 2));
        q.add(new QuizQuestion("بزرگ‌ترین بیابان گرم جهان کدام است؟", "گبی", "صحرا", "آتاباکاما", "قَرَه‌قوم", 1));
        q.add(new QuizQuestion("دریای خزر بین ایران و چند کشور دیگر مشترک است؟", "2", "3", "4", "5", 2)); // ایران + 4 کشور دیگر
        q.add(new QuizQuestion("پایتخت فرانسه کدام است؟", "برلین", "رم", "پاریس", "مادرید", 2));
        q.add(new QuizQuestion("قاره‌ای که استرالیا در آن قرار دارد؟", "آسیا", "اقیانوسیه", "اروپا", "آفریقا", 1));
        q.add(new QuizQuestion("طولانی‌ترین رود جهان معمولاً کدام معرفی می‌شود؟", "آمازون", "نیل", "دانوب", "یانگ‌تسه", 1));
        q.add(new QuizQuestion("بزرگ‌ترین کشور جهان از نظر مساحت؟", "کانادا", "چین", "روسیه", "آمریکا", 2));
        q.add(new QuizQuestion("پایتخت آلمان کدام است؟", "برلین", "مونیخ", "هامبورگ", "کلن", 0));
        q.add(new QuizQuestion("رشته‌کوه زاگرس عمدتاً در کدام کشور است؟", "ترکیه", "ایران", "عراق", "افغانستان", 1));
        q.add(new QuizQuestion("آفریقا چند اقیانوس در اطراف خود دارد؟", "1", "2", "3", "4", 1)); // اطلس و هند
        q.add(new QuizQuestion("پایتخت ایتالیا کدام است؟", "میلان", "ونیز", "رم", "ناپل", 2));
        q.add(new QuizQuestion("رود آمازون در کدام قاره است؟", "آمریکای جنوبی", "آمریکای شمالی", "آسیا", "آفریقا", 0));
        q.add(new QuizQuestion("کشور مصر در کدام قاره است؟", "اروپا", "آسیا", "آفریقا", "اقیانوسیه", 2));
        q.add(new QuizQuestion("قطب جنوب در کدام قاره قرار دارد؟", "آنتارکتیکا", "آسیا", "اروپا", "آفریقا", 0));
        q.add(new QuizQuestion("پایتخت روسیه کدام است؟", "سن‌پترزبورگ", "مسکو", "کازان", "کیف", 1));

        return q;
    }
}
