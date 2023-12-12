package com.kaimuellercode.thecookbook.cookbook.model;

/**
 * Used to Create a New User, Because UserPasswords are not Communicated with the Client,
 * only stored internally
 * @param password the Raw Password
 */

public record UserInitialInformation(String name, String email, String password) {

}
