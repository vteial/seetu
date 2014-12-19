package io.vteial.seetu.service;

import io.vteial.seetu.model.User

public interface AutoNumberService {

	long getNextNumber(String key)

	long getNextNumber(User sessionUser, String key)
}
