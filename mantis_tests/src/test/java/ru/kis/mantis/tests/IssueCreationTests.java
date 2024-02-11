package ru.kis.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.kis.mantis.common.CommonFunc;
import ru.kis.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {
    @Test
    void canCreateIssueSOAP() {
        app.soap().createIssue(new IssueData()
                .withSummary(CommonFunc.randomString(10))
                .withDescription(CommonFunc.randomString(20))
                .withProject(1L)
                .withCategory(1L));

    }

    @Test
    void canCreateIssueREST() {
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunc.randomString(10))
                .withDescription(CommonFunc.randomString(20))
                .withProject(1L)
                .withCategory(1L));

    }
}

