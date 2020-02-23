package com.avaj.decoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.HashMap;
import java.security.MessageDigest;

public final class Decoder {

  private static final HashMap<String, Integer> _rainbowTable = _generateRainbowTable();

  private Decoder() {}

  private static String _hashWithMD5(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hashBytes = md.digest(str.getBytes("UTF-8"));
  
      BigInteger bi = new BigInteger(1, hashBytes);
      return String.format("%0" + (hashBytes.length << 1) + "x", bi); 
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      throw new RuntimeException("Error while generating md5 hash for string \"" + str + "\"");
    }
  }

  private static HashMap<String, Integer> _generateRainbowTable() {
    HashMap<String, Integer> table = new HashMap<>();

    for (int i = 0; i <= 1000000; i++) {
      String key = _hashWithMD5(Integer.toString(i));
      table.put(key, i);
    }

    return table;
  }

  public static Integer rainbowTableLookup(String md5Hash) {
    return Decoder._rainbowTable.get(md5Hash);
  }

}