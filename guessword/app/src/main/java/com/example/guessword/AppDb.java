package com.example.guessword;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {WordEntity.class, UsedWordEntity.class}, version = 2, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile AppDb INSTANCE;

    public static AppDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDb.class,
                                    "guess_game.db"
                            )
                            // ✅ چون ساختار جدول عوض شد (hint اضافه شد)
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        AppDb database = getInstance(context);
                                        seed(database.wordDao());
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void seed(WordDao dao) {
        List<WordEntity> words = Arrays.asList(
                new WordEntity(201, "ایران", "کشوری در غرب آسیا", "geography", false),
                new WordEntity(202, "تهران", "پایتخت ایران", "geography", false),
                new WordEntity(203, "اصفهان", "نصف جهان؛ شهر تاریخی", "geography", false),
                new WordEntity(204, "شیراز", "شهر حافظ و سعدی", "geography", false),
                new WordEntity(205, "تبریز", "مرکز آذربایجان شرقی", "geography", false),
                new WordEntity(206, "مشهد", "شهر حرم امام رضا", "geography", false),
                new WordEntity(207, "کرمان", "استانی در جنوب‌شرق ایران", "geography", false),
                new WordEntity(208, "یزد", "شهر بادگیرها", "geography", false),
                new WordEntity(209, "رشت", "مرکز استان گیلان", "geography", false),
                new WordEntity(210, "اهواز", "مرکز استان خوزستان", "geography", false),
                new WordEntity(211, "کرج", "مرکز استان البرز", "geography", false),
                new WordEntity(212, "قم", "شهری زیارتی در ایران", "geography", false),
                new WordEntity(213, "اراک", "مرکز استان مرکزی", "geography", false),
                new WordEntity(214, "بندرعباس", "مرکز استان هرمزگان", "geography", false),
                new WordEntity(215, "دریایخزر", "بزرگ‌ترین دریاچه جهان", "geography", false),
                new WordEntity(216, "خلیجفارس", "آبراه معروف جنوب ایران", "geography", false),
                new WordEntity(217, "البرز", "رشته‌کوه شمال ایران", "geography", false),
                new WordEntity(218, "زاگرس", "رشته‌کوه غرب ایران", "geography", false),
                new WordEntity(219, "کویرلوت", "یکی از گرم‌ترین نقاط زمین", "geography", false),
                new WordEntity(220, "کارون", "رودی معروف در خوزستان", "geography", false)
        );

        dao.insertWords(words);
    }
}
