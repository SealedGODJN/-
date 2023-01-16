package com.NPU.fuck;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckIdentifyCode {
    private void BtnOK_Click(String name, String code) {
        if (isNullOrEmpty(code)) {
            return;
        }

        Pattern p = Pattern.compile("[1-9]");
        Matcher m = p.matcher(code);
        if (m.lookingAt()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }
}
