/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mini.payment.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Encryption Utils
 */
public class EncryptUtil {

    private static final Log LOG = LogFactory.getLog(EncryptUtil.class);

    // add salt
    public static final String PWDSALT = "PAY";

    private EncryptUtil() {
    }

    /**
     * Encrypt via MD5 algorithm
     *
     * @param str content to be encrypted
     * @return
     */
    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    /**
     * Encrypt via SHA algorithm
     *
     * @param str content to be encrypted
     * @return SHA encrypted return value
     */
    public static String encodeSHAString(String str) {
        return encode(str, "SHA");
    }

    /**
     * Encrypt via base64
     *
     * @param str content to be encrypted
     * @return base64 encrypted return value
     */
    public static String encodeBase64String(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    /**
     * Decryption via base64 algorithm
     *
     * @param str content to be encrypted
     * @return base64 decrypted return value
     * @throws IOException
     */
    public static String decodeBase64String(String str) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }

    private static String encode(String str, String method) {
        MessageDigest mdInst = null;
        StringBuilder dstr = new StringBuilder();
        try {
            mdInst = MessageDigest.getInstance(method);
            mdInst.update(str.getBytes());
            byte[] md = mdInst.digest();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0) {
                    tmp += 256;
                }
                if (tmp < 16) {
                    dstr.append("0");
                }
                dstr.append(Integer.toHexString(tmp));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e);
        }
        return dstr.toString();
    }
}
