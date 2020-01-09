package edu.lab.back.dtoPojos.db.usertypes;

import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;

import java.sql.Types;

public class ChangesOnTableUserType extends BaseUserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.JAVA_OBJECT};
    }

    @Override
    public Class returnedClass() {
        return ChangesOnTableJson.class;
    }

}
