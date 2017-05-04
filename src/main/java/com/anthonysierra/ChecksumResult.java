package com.anthonysierra;

/**
 * This class is meant to be used for checking the result generated from the @see ChecksumGenerator class.
 * You can use the two values, succeeded and result to get whether the operation you did worked or not, along with its outputted value.
 */
final public class ChecksumResult {
    /**
     * Whether the operation succeeded.
     */
    final private boolean succeeded;
    /**
     * The result of the operation that a client runs on ChecksumGenerator.
     */
    final private String result;
    /**
     * The error message to be used when succeeded is false.
     */
    final private String errorMessage;

    ChecksumResult(final boolean succeeded, final String result, final String errorMessage) {
        this.succeeded = succeeded;
        this.result = result;
        this.errorMessage = errorMessage;
    };

    /**
     * Returns whether the operation on ChecksumGenerator was a success and it is safe to use the result of it's computation.
     * @return True for success otherwise false.
     */
    public boolean didSucceed() {
        return this.succeeded;
    };

    /**
     * Will return the result of an operation on ChecksumGenerator.
     * @return Null for an unsuccessful operation, or a filled 0 padded hex string with the result of the ChecksumGenerator's operation.
     */
    public String getResult() {
        return this.result;
    };

    /**
     * This will return the error message for when an operation went wrong with ChecksumGenerator.
     * You can check whether there was an issue by using the didSucceed method.
     * @return Null when no issue, otherwise a string representing the error that occured.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    };
};
