package edu.lab.back.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
//TODO наследовать это и остальные типовые пожики от одного интерфейса, чтоб пилить общие методы для них
public enum ChangeTypeEnum {

    CREATE(1, "create"),
    UPDATE(2, "update"),
    DELETE(3, "delete");

    @Getter
    private Integer id;

    @Getter
    private String name;

}
