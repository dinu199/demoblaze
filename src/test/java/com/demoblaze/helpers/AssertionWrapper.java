package com.demoblaze.helpers;

import com.demoblaze.pom.pages.Page;

import static org.assertj.core.api.Assertions.assertThat;

public final class AssertionWrapper {

    public static void assertThatPageIsOpened(Page page) {
        assertThat(page.isAt()).as(page.getClass().getSimpleName() + " is not displayed.").isTrue();
    }
}
