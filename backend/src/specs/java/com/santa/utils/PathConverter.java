package com.santa.utils;

import cucumber.api.Transformer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConverter extends Transformer<Path> {
    @Override
    public Path transform(String value) {
        return Paths.get(value);
    }
}
