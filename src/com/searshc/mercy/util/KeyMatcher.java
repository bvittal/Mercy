package com.searshc.mercy.util;

import java.util.Arrays;
import org.apache.log4j.Logger;

public class KeyMatcher {
  private static Logger logger = Logger.getLogger(KeyMatcher.class);

    private String key;
    private char[] inquiry;

    public KeyMatcher(String key, char[] inquiry) {
        this.key = key;
        this.inquiry = inquiry;
    }

    public boolean isMatch() {
        boolean match = true;

        char[] keyChars = key.toCharArray();
        char[] requestChars = Arrays.copyOfRange(inquiry, 0, keyChars.length);
        int charIndex = 0;

        for (char keyChar : keyChars) {
            char requestChar = requestChars[charIndex];
            if (keyChar == '+') {
                break;
            }
            if (!charIsMatch(keyChar, requestChar)) {
                match = false;
                break;
            }
            charIndex++;
        }

        if (match) {
          logger.info("RegOp -> key    : '" + key + "' length: " + key.length());
          logger.info("RegOp -> request: '" + new String(requestChars)+"' length: " + requestChars.length);
          logger.info("isMatch : " + match);
        }
        return match;
    }

    private boolean charIsMatch(char keyChar, char requestChar) {
        boolean match;
        if (keyChar == '?') {
            match = true;
        } else if (keyChar == requestChar) {
            match = true;
        } else if (keyChar == 'N' && isCharNumeric(requestChar)) {
            match = true;
        } else if (keyChar == 'Z' && isCharAlphabetic(requestChar)) {
            match = true;
        } else {
            match = false;
        }
        return match;
    }

    private boolean isCharNumeric(char requestChar) {
        return Character.isDigit(requestChar);
    }

    @SuppressWarnings("unused")
    private boolean isCharAlphaNumeric(char requestChar) {
        return Character.isLetterOrDigit(requestChar);
    }

    private boolean isCharAlphabetic(char requestChar) {
        return Character.isLetter(requestChar);
    }

}
