package com.nathanstruhs.namemangler;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MangledNameTest {
    private MangledName subject;

    @Before
    public void setUp() throws Exception {
        String name = "Example Name";
        String randomWord = "Gooood";
        subject = new MangledName(name, randomWord);
    }

    @Test
    public void toStringReturnsMangledName() {
        assertThat(subject.toString(), is("Example Name Gooood"));
    }
}