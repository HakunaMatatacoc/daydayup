package com.jianli.util.optional;

import lombok.Data;

import java.util.Optional;

@Data
public class NewMan {
    private Optional<Godness> godness = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }
}
