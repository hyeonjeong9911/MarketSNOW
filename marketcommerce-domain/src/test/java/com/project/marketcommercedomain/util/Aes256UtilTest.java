package com.project.marketcommercedomain.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Aes256UtilTest {

  @Test
  void encrypt() {
    String encrypt = Aes256Util.encrypt("This is my Test");
    assertEquals(Aes256Util.decrypt(encrypt), "This is my Test");
  }

}