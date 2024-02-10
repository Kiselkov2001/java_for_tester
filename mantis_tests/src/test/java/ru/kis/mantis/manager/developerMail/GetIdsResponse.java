package ru.kis.mantis.manager.developerMail;

import java.util.List;

public record GetIdsResponse(boolean success, Object errors, List<String> result) {
}
