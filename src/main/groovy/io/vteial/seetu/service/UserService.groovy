package io.vteial.seetu.service;

import io.vteial.seetu.model.User
import io.vteial.seetu.service.exceptions.ModelAlreadyExistException;

public interface UserService {

	void add(User sessionUser, User user) throws ModelAlreadyExistException
}
