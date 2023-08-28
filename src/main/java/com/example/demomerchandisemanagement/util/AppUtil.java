package com.example.demomerchandisemanagement.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * アプリケーション共通クラス
 */
public class AppUtil {

    /**
     * メッセージIDからメッセージを取得する
     *
     * @param messageSource メッセージソース
     * @param key           メッセージキー
     * @param params        置換文字群
     * @return メッセージ
     */
    public static String getMessage(MessageSource messageSource, String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }
}
