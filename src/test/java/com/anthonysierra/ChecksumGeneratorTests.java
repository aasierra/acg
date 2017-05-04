package com.anthonysierra;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony on 5/3/2017.
 */
public class ChecksumGeneratorTests {
    @Test
    public void fileInputCorrect() {
        final ChecksumResult result = ChecksumGenerator.generateSha256(new File("src/test/resources/basic.txt"));
        assertEquals("c8b2a3e8db7ba665aec5e30031d10833a1def26296be52530d4c9d5751c830a8", result.getResult());
        assertEquals(null, result.getErrorMessage());
        assertEquals(true, result.didSucceed());
    }
}
