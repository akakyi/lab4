package edu.lab.back.util.converters.adapters;

import edu.lab.back.util.ProfileTypeEnum;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ProfileTypeAdapter extends XmlAdapter<String, ProfileTypeEnum> {

    @Override
    public ProfileTypeEnum unmarshal(String v) throws Exception {
        final ProfileTypeEnum result = ProfileTypeEnum.getEnumByName(v);

        return result;
    }

    @Override
    public String marshal(ProfileTypeEnum v) throws Exception {
        final String result = v.getName();

        return result;
    }

}
