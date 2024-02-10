package ru.kis.mantis.manager.developerMail;

import ru.kis.mantis.model.DeveloperMailUser;

public record AddUserResponse(boolean success, Object errors, DeveloperMailUser result) {
}
