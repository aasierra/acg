package com.anthonysierra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class can be used by clients to generate a checksum for a downloaded file.
 */
final public class ChecksumGenerator {

    private static final String SHA_256 = "SHA-256";

    /**
     * Generate a 0 padded SHA-256 hex version of the file.
     * @param file The file to generate the SHA-256 has for.
     * @return The checksum result which contains the result, error messages, as well as if the operation succeeded.
     */
    public static ChecksumResult generateSha256(final File file) {
        return generate(file, SHA_256);
    };

    /**
     * A flexible private method to be called with various files and algorithms for generating a 0 padded checksum of a file.
     * @param file The file that the checksum needs to be calculated for.
     * @param algorithm The algorithm to use with the file.
     * @return The checksum representing the file with the algorithm that was used for calculation with 0 padding.
     */
    private static ChecksumResult generate(final File file, final String algorithm) {
        ChecksumResult result;
        try (final FileInputStream stream = new FileInputStream(file)) {
            final MessageDigest digest = getPopulatedDigest(stream, algorithm);
            final byte[] messageDigest = digest.digest();
            final StringBuilder bldr = getPopulatedBuilder(messageDigest);
            result = new ChecksumResult(true, bldr.toString(), null);
        } catch (final NoSuchAlgorithmException nsae) {
            result = new ChecksumResult(false, null, "Could not find the algorithm " + algorithm + " \n " + nsae.getMessage());
        } catch (final FileNotFoundException fnfe) {
            result = new ChecksumResult(false, null, "Could not find the file with path " + file.getAbsolutePath() + " \n " + fnfe.getMessage());
        } catch (final SecurityException se) {
            result = new ChecksumResult(false, null, "Security issue, could not access the file " + file.getAbsolutePath() + " possibly due to permissions errors. \n " + se.getMessage());
        } catch (final IOException ioe) {
            result = new ChecksumResult(false, null, "IO error occurred accesing file " + file.getAbsolutePath() + " with algorithm " + algorithm + " \n " + ioe.getMessage());
        };
        return result;
    };

    /**
     * Takes in the byte array from a MessageDigest object and will make a StringBuilder object with 0 padded hex representation of the byte array;
     * @param messageDigest An array of bytes that should have come from the digest method of a MessageDigest object.
     * @return A String builder containing the 0 padded hex representation of the byte array;
     */
    private static StringBuilder getPopulatedBuilder(final byte[] messageDigest) {
        final StringBuilder bldr = new StringBuilder();
        for (int i = 0 ; i < messageDigest.length; i++) {
            bldr.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
        };
        return bldr;
    };

    /**
     * This must be an already opened and ready to read file input stream.
     * @param stream A fully prepared FileInputStream object.
     */
    private static MessageDigest getPopulatedDigest(final FileInputStream stream, final String algorithm) throws IOException, NoSuchAlgorithmException{
        final MessageDigest digest = MessageDigest.getInstance(algorithm);
        final byte[] bytes = new byte[1024];
        int amountToRead;
        while ((amountToRead = stream.read(bytes)) != -1) {
            digest.update(bytes, 0, amountToRead);
        };
        return digest;
    };
};
