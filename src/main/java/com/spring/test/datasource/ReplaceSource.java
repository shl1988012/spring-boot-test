package com.spring.test.datasource;

import org.springframework.core.io.AbstractResource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;


@Component
public class ReplaceSource extends AbstractResource {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
